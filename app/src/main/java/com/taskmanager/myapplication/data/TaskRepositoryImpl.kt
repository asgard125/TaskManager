package com.taskmanager.myapplication.data

import com.taskmanager.myapplication.data.room.dao.TaskDao
import com.taskmanager.myapplication.domain.TaskRepository
import com.taskmanager.myapplication.domain.models.Task

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {

    private val mapper = Mapper()

    override suspend fun addTask(task: Task) {
        taskDao.addTask(mapper.taskToTaskEntity(task))
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(mapper.taskToTaskEntity(task))
    }
    override suspend fun getAllTasks(): List<Task>{
    return taskDao.getAllTasks().map {
        mapper.taskEntityToTask(it)
        }
    }
    override suspend fun getTasksFromTaskList(id: Int): List<Task> {
        return taskDao.getTasksFromTaskList(id).map {
            mapper.taskEntityToTask(it)
        }
    }
    override suspend fun getFavoriteTasks(): List<Task> {
        return taskDao.getFavoriteTasks().map {
            mapper.taskEntityToTask(it)
        }
    }
}