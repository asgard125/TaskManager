package com.taskmanager.myapplication.domain.usecase

import com.taskmanager.myapplication.domain.TaskListRepository
import com.taskmanager.myapplication.domain.models.TaskList

class AddTaskListUseCase(private val taskListRepository: TaskListRepository) {
    suspend fun execute(name: String){
        val taskList = TaskList(name)
        taskListRepository.addTaskList(taskList)
    }
}