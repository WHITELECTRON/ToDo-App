package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.data.room_database.TaskDatabase
import com.example.todoapp.data.room_database.Taskitem
import com.example.todoapp.repository.TaskRepository
import kotlinx.coroutines.flow.StateFlow

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application){

    private val dao= TaskDatabase.getDatabase(application).taskDao()

    private val repository= TaskRepository(dao)

    val allTasks: StateFlow<List<Taskitem>> = repository.getAllTasks()
            .stateIn(viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )
    fun addTask(task: Taskitem){
        viewModelScope.launch {
            repository.insert(task)
        }
    }
    fun updateTask(task: Taskitem){
        viewModelScope.launch{
            repository.update(task)

        }
    }
    fun deleteTask(task: Taskitem){
        viewModelScope.launch{
            repository.delete(task)
        }
    }
}