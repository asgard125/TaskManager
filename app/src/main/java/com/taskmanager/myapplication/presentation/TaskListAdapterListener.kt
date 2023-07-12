package com.taskmanager.myapplication.presentation

import com.taskmanager.myapplication.domain.models.Task

interface TaskListAdapterListener {

    fun onChanged(task: Task)

    fun onDelete(task: Task)

    fun onClick(id: Int){}
}