package com.taskmanager.myapplication.presentation

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter.POSITION_NONE
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.taskmanager.myapplication.domain.models.TaskList


class ViewPagerAdapter(fragmentActivity: MainActivity) : FragmentStateAdapter(fragmentActivity) {

    private var tasksLists: MutableList<TaskList> = mutableListOf()

    override fun getItemCount(): Int {
        // добавление 2 т.к есть список общих задач и избранных
        return tasksLists.size + 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0){
            return TaskListFragment.newInstance(-2)
        } else if (position == 1){
            return TaskListFragment.newInstance(-1)
        }
        val pos = tasksLists[position - 2].id
        if (pos != null) {
            return TaskListFragment.newInstance(pos)
        }else{
            return TaskListFragment.newInstance(-1)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newTaskList: List<TaskList>){
        tasksLists = newTaskList.toMutableList()
        notifyDataSetChanged()
    }
}