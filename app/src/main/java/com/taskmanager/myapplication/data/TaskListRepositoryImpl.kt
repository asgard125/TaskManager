package com.taskmanager.myapplication.data

import androidx.lifecycle.LiveData
import com.taskmanager.myapplication.data.room.dao.TaskListDao
import com.taskmanager.myapplication.domain.TaskListRepository
import com.taskmanager.myapplication.domain.models.TaskList

class TaskListRepositoryImpl(private val taskListDao: TaskListDao) : TaskListRepository {

    override fun addTaskList(taskList: TaskList) {
        taskListDao.addTaskList(taskList)
    }

    override fun deleteTaskList(taskList: TaskList) {
        taskListDao.deleteTaskList(taskList)
    }

    override fun updateTaskList(taskList: TaskList) {
        taskListDao.updateTaskList(taskList)
    }

    override fun getAllTaskLists(): LiveData<List<TaskList>> {
        return taskListDao.getAllTaskLists()
    }

    override fun getTaskListById(id: Int): LiveData<List<TaskList>> {
        return taskListDao.getTaskListById(id)
    }
}