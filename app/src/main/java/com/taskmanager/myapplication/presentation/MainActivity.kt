package com.taskmanager.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.taskmanager.myapplication.databinding.ActivityMainBinding
import com.taskmanager.myapplication.di.Dependencies

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var vpAdapter: ViewPagerAdapter
    lateinit var vm: MainViewModel
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



        binding.addTaskListButton.setOnClickListener {
            startActivity(
                vm.taskLists.value?.get(tabIndex)?.let { it1 -> TaskActivity.getIntent(this, it1.id) })
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
}