package com.taskmanager.myapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.taskmanager.myapplication.R
import com.taskmanager.myapplication.databinding.ActivityMainBinding
import com.taskmanager.myapplication.di.Dependencies

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var vpAdapter: ViewPagerAdapter
    lateinit var vm: MainViewModel
    lateinit var dialog: BottomSheetDialog
    var tabIndex: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[MainViewModel::class.java]

        Dependencies.taskRepository


        vm.taskLists.observe(this){
            vpAdapter = ViewPagerAdapter(this, it)
            binding.viewPager.adapter = vpAdapter
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
                tab.text = it[pos].name
            }.attach()
        }

        fun showSoftKeyboard(view: View) {
            if (view.requestFocus()) {
                val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            }
        }

        fun showBottomSheetDialog() {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
            dialog = BottomSheetDialog(this, R.style.AddTaskDialogTheme)
            dialog.setContentView(dialogView)
            dialog.show()
        }

        binding.addTaskListButton.setOnClickListener {
            showBottomSheetDialog()
        }

        vm.getAllTAskList()

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabIndex = tab!!.position
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
        vm.getAllTAskList()
    }

    companion object {
        fun getIntent(context: Context) : Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
}