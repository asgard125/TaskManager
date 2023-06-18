package com.taskmanager.myapplication.domain

import com.taskmanager.myapplication.domain.models.TaskList

interface TaskListRepository {
    suspend fun addTaskList(taskList: TaskList)
    suspend fun deleteTaskList(taskList: TaskList)
    suspend fun updateTaskList(taskList: TaskList)
    suspend fun getAllTaskLists() : List<TaskList>
}