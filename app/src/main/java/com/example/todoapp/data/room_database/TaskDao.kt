package com.example.todoapp.data.room_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Taskitem)

    @Update
    suspend fun update(task: Taskitem)

    @Delete
    suspend fun delete(task: Taskitem)

    @Query(value="SELECT* FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<Taskitem>>


}