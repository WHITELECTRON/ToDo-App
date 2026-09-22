package com.example.todoapp.repository

import com.example.todoapp.data.room_database.TaskDao
import com.example.todoapp.data.room_database.Taskitem
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    fun getAllTasks(): Flow<List<Taskitem>> {
        return dao.getAllTasks()

    }
    suspend fun insert(task: Taskitem)=dao.insert(task)
    suspend fun update(task: Taskitem)=dao.update(task)
    suspend fun delete(task: Taskitem)=dao.delete(task)
}