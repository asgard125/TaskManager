package com.taskmanager.myapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.taskmanager.myapplication.R
import com.taskmanager.myapplication.databinding.ActivityTaskBinding
import com.taskmanager.myapplication.domain.models.Task
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityTaskBinding

    val taskID by lazy { intent.getIntExtra(ARG_TASK_ID, 0)}
    lateinit var task: Task
    lateinit var dialog: BottomSheetDialog
    lateinit var vm: TaskViewModel

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[TaskViewModel::class.java]
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.getTaskById(taskID)
        vm.task.observe(this) {
            updateTaskData(it)
        }

        fun showBottomSheetDialogChangeDesc() {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_description, null)
            dialog = BottomSheetDialog(this, R.style.AddTaskDialogTheme)
            dialog.setContentView(dialogView)
            dialog.show()

            dialog.findViewById<Button>(R.id.change_desc_button)?.setOnClickListener {
                task.description = dialog.findViewById<EditText>(R.id.taskDescription)?.text.toString()
                GlobalScope.launch {
                    vm.changeTask(task)
                }
                dialog.dismiss()
            }

        }

        binding.taskDescription.setOnClickListener{
            showBottomSheetDialogChangeDesc()
        }


    }
    companion object {
        private const val ARG_TASK_ID = "taskListID"
        fun getIntent(context: Context, id: Int) : Intent{
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(ARG_TASK_ID, id)
            return intent
        }
    }
    fun updateTaskData(taskData: List<Task>){
        task = taskData[0]
        binding.taskTitle.text = task.name
        binding.taskDescription.text = task.description
    }
}