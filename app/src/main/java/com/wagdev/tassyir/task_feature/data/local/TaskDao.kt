package com.wagdev.tassyir.task_feature.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.wagdev.tassyir.task_feature.domain.Model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>

    @Upsert
    suspend fun insertTask(task:Task)
    @Delete
    suspend fun deleteTask(task: Task)
    @Query("SELECT * FROM task WHERE id=:id")
    suspend fun getTaskById(id:Int):Task?

}