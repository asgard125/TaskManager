package com.taskmanager.myapplication.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {

    val list = MutableLiveData<List<Task>>()

    private val taskRepository = Dependencies.taskRepository



    fun getTasksFromTaskList(id: Int) {
        viewModelScope.launch {
            list.postValue(taskRepository.getTasksFromTaskList(id))
        }
    }
}