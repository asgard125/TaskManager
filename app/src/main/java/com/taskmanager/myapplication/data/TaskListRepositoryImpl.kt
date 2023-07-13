package com.taskmanager.myapplication.data

import com.taskmanager.myapplication.data.room.dao.TaskListDao
import com.taskmanager.myapplication.domain.TaskListRepository
import com.taskmanager.myapplication.domain.models.TaskList

class TaskListRepositoryImpl(private val taskListDao: TaskListDao) : TaskListRepository {

    override suspend fun addTaskList(taskList: TaskList) {
        taskListDao.addTaskList(taskList)
    }

    override suspend fun deleteTaskList(taskList: TaskList) {
        taskListDao.deleteTaskList(taskList)
    }

    override suspend fun updateTaskList(taskList: TaskList) {
        taskListDao.updateTaskList(taskList)
    }

    override suspend fun getAllTaskLists(): List<TaskList> {
        return taskListDao.getAllTaskLists().map {
            TaskList(it.id, it.name)
        }
    }
}