package com.taskmanager.myapplication.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.taskmanager.myapplication.domain.models.Task
import com.taskmanager.myapplication.domain.models.TaskList

class ViewPagerAdapter(fragmentActivity: MainActivity) : FragmentStateAdapter(fragmentActivity) {

    val taskList = MutableLiveData<List<Task>>()
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return TaskListFragment.newInstance(position)
    }
}