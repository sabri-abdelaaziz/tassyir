package com.wagdev.tassyir.goal_feature.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.wagdev.tassyir.goal_feature.domain.model.Goal
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Query("SELECT * FROM goal")
    fun getAllGoals(): Flow<List<Goal>>
    @Query("SELECT * FROM goal ORDER BY title")
    fun getGoalsByTitle(): Flow<List<Goal>>
    @Query("SELECT * FROM goal ORDER BY deadline")
    fun getGoalsByDate(): Flow<List<Goal>>
    @Query("SELECT * FROM goal WHERE id=:id")
    suspend fun getGoalById(id:Int):Goal?

    @Upsert
    suspend fun addGoal(goal: Goal)
    @Delete
    suspend fun deleteGoal(goal: Goal)





}