package com.taskmanager.myapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taskmanager.myapplication.databinding.DialogAddTaskBinding
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskActivity : AppCompatActivity() {

    lateinit var binding: DialogAddTaskBinding

    val taskListID by lazy { intent.getIntExtra(ARG_TASK_LIST_ID, 0)}

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createButton.setOnClickListener {
            val title = binding.taskTitle.text.toString()
            val desc = ""
            GlobalScope.launch {
                Dependencies.taskRepository.addTask(Task(name=title, description = desc, taskListId = taskListID, favorite = false, completed = false))
            }
            startActivity(
                MainActivity.getIntent(this)
            )

        }
    }

    companion object {
        private const val ARG_TASK_LIST_ID = "taskListID"
        fun getIntent(context: Context, taskListId: Int) : Intent{
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(ARG_TASK_LIST_ID, taskListId)
            return intent
        }
    }
}