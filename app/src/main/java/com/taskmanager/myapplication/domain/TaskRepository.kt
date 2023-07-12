package com.taskmanager.myapplication.domain

import com.taskmanager.myapplication.domain.models.Task

interface TaskRepository {
    suspend fun addTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun getAllTasks(): List<Task>
    suspend fun getTasksFromTaskList(id: Int): List<Task>
    suspend fun getFavoriteTasks(): List<Task>
}