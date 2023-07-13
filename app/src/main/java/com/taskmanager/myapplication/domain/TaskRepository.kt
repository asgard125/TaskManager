package com.taskmanager.myapplication.domain

import androidx.lifecycle.LiveData
import com.taskmanager.myapplication.domain.models.Task

interface TaskRepository {
    fun addTask(task: Task)
     fun deleteTask(task: Task)
     fun getTaskById(id: Int): LiveData<List<Task>>
     fun changeTask(task: Task)
     fun getAllTasks(): LiveData<List<Task>>
    fun getTasksFromTaskList(id: Int): LiveData<List<Task>>
    fun getFavoriteTasks(): LiveData<List<Task>>
}