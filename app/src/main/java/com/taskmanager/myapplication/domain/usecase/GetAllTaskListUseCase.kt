package com.taskmanager.myapplication.domain.usecase

import com.taskmanager.myapplication.domain.TaskListRepository
import com.taskmanager.myapplication.domain.models.TaskList

class GetAllTaskListUseCase(private val taskListRepository: TaskListRepository) {
    suspend fun execute() : List<TaskList>{
        return taskListRepository.getAllTaskLists()
    }
}