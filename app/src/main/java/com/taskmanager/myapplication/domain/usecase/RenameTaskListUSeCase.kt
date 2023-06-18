package com.taskmanager.myapplication.domain.usecase

import com.taskmanager.myapplication.domain.TaskListRepository
import com.taskmanager.myapplication.domain.models.TaskList

class RenameTaskListUSeCase(private val repository: TaskListRepository) {
    suspend fun execute(taskList: TaskList, newName: String) {
        taskList.name = newName
        repository.updateTaskList(taskList)
    }
}