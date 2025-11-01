package com.example.todolistapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.todolistapp.data.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY task_activated DESC, task_date, task_time")
    suspend fun loadTasks(): List<Task>

    @Insert
    suspend fun createTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Update
    suspend fun setChecked(task: Task)

    @Query("SELECT * FROM task WHERE task_title like '%' || :queryWord || '%'")
    suspend fun search(queryWord: String): List<Task>


    @Dao
    interface TaskDao {

        @Query("SELECT * FROM task ORDER BY task_id DESC")
        suspend fun loadTasks(): List<Task>

        @Query("SELECT * FROM task WHERE task_title LIKE :query")
        suspend fun search(query: String): List<Task>

        @Insert
        suspend fun createTask(task: Task)

        @Update
        suspend fun updateTask(task: Task)

        @Query("UPDATE task SET task_activated = :activated WHERE task_id = :id")
        suspend fun setCheckedById(id: Int, activated: Int)

    }

}