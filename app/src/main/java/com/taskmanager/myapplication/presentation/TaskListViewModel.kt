package com.taskmanager.myapplication.presentation
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.taskmanager.myapplication.di.Dependencies
//import com.taskmanager.myapplication.domain.models.Task
//import kotlinx.coroutines.launch
//
//class TaskListViewModel : ViewModel() {
//
//    val list = MutableLiveData<List<Task>>()
//
//    private val taskRepository = Dependencies.taskRepository
//
//    fun getAllTasks(){
//        viewModelScope.launch {
//            list.postValue(taskRepository.getAllTasks())
//        }
//    }
//
//    fun getTasksFromTaskList(id: Int) {
//        viewModelScope.launch {
//            list.postValue(taskRepository.getTasksFromTaskList(id))
//        }
//    }
//
//    fun getFavoriteTasks() {
//        viewModelScope.launch {
//            list.postValue(taskRepository.getFavoriteTasks())
//        }
//    }
//    fun changeTask(){
//        viewModelScope.launch {
//            list.postValue(taskRepository.getFavoriteTasks())
//        }
//    }
//
//    fun deleteTask(task: Task){
//        viewModelScope.launch {
//            taskRepository.deleteTask(task)
//        }
//    }
//}