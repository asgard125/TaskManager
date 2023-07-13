package com.taskmanager.myapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator
import com.taskmanager.myapplication.R
import com.taskmanager.myapplication.databinding.ActivityMainBinding
import com.taskmanager.myapplication.databinding.DialogAddTaskBinding
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
import com.taskmanager.myapplication.domain.models.TaskList
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var addTaskBinding: DialogAddTaskBinding
    lateinit var vpAdapter: ViewPagerAdapter
    lateinit var vm: MainViewModel
    lateinit var dialog: BottomSheetDialog
    var tabIndex: Int = 0

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        addTaskBinding = DialogAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[MainViewModel::class.java]
        vpAdapter = ViewPagerAdapter(this)

        Dependencies.taskRepository

        fun showBottomSheetDialogAddTask() {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
            dialog = BottomSheetDialog(this, R.style.AddTaskDialogTheme)
            dialog.setContentView(dialogView)
            dialog.show()

            dialog.findViewById<Button>(R.id.create_task_button)?.setOnClickListener {
                val title = dialog.findViewById<EditText>(R.id.taskTitle)?.text.toString()
                val desc = ""
                GlobalScope.launch {
                    vm.addTask(Task(name = title, description = desc, taskListId = 0, favorite = false, completed = false))
                }
                dialog.dismiss()
            }

        }


        binding.addTaskButton.setOnClickListener {
            showBottomSheetDialogAddTask()
        }


        binding.viewPager.apply {
            adapter = vpAdapter
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tabLayout.getTabAt(position)!!.select()
                    tabIndex = position
                }
            })
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                return
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                return
            }
        })

    }

    fun startTaskActivity(id: Int){
        startActivity(
            TaskActivity.getIntent(this, id))
    }

    companion object {
        fun getIntent(context: Context) : Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
}