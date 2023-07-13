package com.taskmanager.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.taskmanager.myapplication.data.room.dao.TaskDao
import com.taskmanager.myapplication.domain.TaskRepository
import com.taskmanager.myapplication.domain.models.Task

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {

    override fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    override fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
    override fun changeTask(task: Task) {
        taskDao.changeTask(task)
    }
    override fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    override fun getTaskById(id: Int): LiveData<List<Task>> {
        return taskDao.getTaskById(id)
    }
    override fun getTasksFromTaskList(id: Int): LiveData<List<Task>> {
        return taskDao.getTasksFromTaskList(id)
    }
    override fun getFavoriteTasks(): LiveData<List<Task>> {
        return taskDao.getFavoriteTasks()
    }
}