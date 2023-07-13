package com.taskmanager.myapplication.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
import com.taskmanager.myapplication.domain.models.TaskList
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
//    val list = MutableLiveData<List<Task>>()
    var list: LiveData<List<Task>> = Dependencies.taskRepository.getAllTasks()

    private val taskRepository = Dependencies.taskRepository
    fun getAllTasks(){
        viewModelScope.launch {
            list = taskRepository.getAllTasks()
        }
        Log.d("obs", list.hasActiveObservers().toString())
    }

    fun getTasksFromTaskList(id: Int) {
        viewModelScope.launch {
            list = taskRepository.getTasksFromTaskList(id)
        }
    }

    fun getFavoriteTasks() {
        viewModelScope.launch {
            list= taskRepository.getFavoriteTasks()
        }
    }

    fun addTask(task: Task){
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }
    fun changeTask(){
        viewModelScope.launch {
//            list.postValue(taskRepository.getFavoriteTasks())
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}