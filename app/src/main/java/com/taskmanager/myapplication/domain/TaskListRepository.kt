package com.taskmanager.myapplication.domain

import androidx.lifecycle.LiveData
import com.taskmanager.myapplication.domain.models.Task
import com.taskmanager.myapplication.domain.models.TaskList

interface TaskListRepository {
     fun addTaskList(taskList: TaskList)
     fun deleteTaskList(taskList: TaskList)
     fun updateTaskList(taskList: TaskList)
     fun getAllTaskLists() : LiveData<List<TaskList>>
     fun getTaskListById(id: Int): LiveData<List<TaskList>>
}