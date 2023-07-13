package com.taskmanager.myapplication.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taskmanager.myapplication.databinding.FragmentTaskListBinding
import com.taskmanager.myapplication.domain.models.Task
import java.util.*


class TaskListFragment() : Fragment(), TaskListAdapterListener {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    lateinit var  hostActivity: MainActivity
    private val adapter: TaskListAdapter = TaskListAdapter(this)
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.rv.adapter = adapter
        if (requireArguments().getInt(ARG_TASK_LIST_ID) == 0){
            viewModel.getFavoriteTasks()
        }
        else if (requireArguments().getInt(ARG_TASK_LIST_ID) == 1){
            viewModel.getAllTasks()
        } else{
            viewModel.getTasksFromTaskList(requireArguments().getInt(ARG_TASK_LIST_ID) - 1)
        }
        viewModel.list.observe(this) {
            adapter.updateData(it)
        }

    }

    companion object {
        private const val ARG_TASK_LIST_ID = "task_list_id"
        fun newInstance(type: Int):TaskListFragment{
            val arg = bundleOf(
                ARG_TASK_LIST_ID to type
            )

            return TaskListFragment().apply {
                arguments = arg
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onChanged(task: Task) {
        // todo
    }
    override fun onDelete(task: Task) {
        viewModel.deleteTask(task)
    }

    override fun onClick(id: Int) {
        // todo
    }
}