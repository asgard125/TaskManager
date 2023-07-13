package com.taskmanager.myapplication.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
import com.taskmanager.myapplication.domain.models.TaskList
import kotlinx.coroutines.launch

class TaskViewModel: ViewModel() {
    var task: LiveData<List<Task>> = Dependencies.taskRepository.getAllTasks()

    private val taskRepository = Dependencies.taskRepository

    fun getTaskById(id: Int){
        viewModelScope.launch {
            task = taskRepository.getTaskById(id)
        }
    }

    fun changeTask(task: Task){
        viewModelScope.launch {
            taskRepository.changeTask(task)
        }
    }
}