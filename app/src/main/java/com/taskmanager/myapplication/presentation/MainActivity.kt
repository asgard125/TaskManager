package com.taskmanager.myapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.taskmanager.myapplication.R
import com.taskmanager.myapplication.databinding.ActivityMainBinding
import com.taskmanager.myapplication.databinding.DialogAddTaskBinding
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
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

        fun showBottomSheetDialog() {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
            dialog = BottomSheetDialog(this, R.style.AddTaskDialogTheme)
            dialog.setContentView(dialogView)
            dialog.show()

            dialog.findViewById<Button>(R.id.create_button)?.setOnClickListener {
                val title = dialog.findViewById<EditText>(R.id.taskTitle)?.text.toString()
                val desc = ""
                GlobalScope.launch {
                    vm.addTask(Task(name = title, description = desc, taskListId = 0, favorite = false, completed = false))
                }
                get_tasks_by_tab_index(tabIndex)
                dialog.dismiss()
            }

        }

        binding.addTaskListButton.setOnClickListener {
            showBottomSheetDialog()
        }


        binding.viewPager.apply {
            adapter = vpAdapter
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tabLayout.getTabAt(position)!!.select()
                    tabIndex = position
                    get_tasks_by_tab_index(tabIndex)
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

    override fun onResume() {
        super.onResume()
        get_tasks_by_tab_index(tabIndex)
    }
    fun get_tasks_by_tab_index(tab_index: Int){
        if (tab_index == 0){
            vm.getFavoriteTasks()
        }
        else if (tab_index == 1){
            vm.getAllTasks()
        } else{
            vm.getTasksFromTaskList(tab_index - 1)
        }
    }

    companion object {
        fun getIntent(context: Context) : Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
}