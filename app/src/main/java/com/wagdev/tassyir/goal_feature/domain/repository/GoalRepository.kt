package com.wagdev.tassyir.goal_feature.domain.repository

import com.wagdev.tassyir.goal_feature.domain.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {
    fun getGoals(): Flow<List<Goal>>
    fun getGoalsByTitle():Flow<List<Goal>>
    fun getGoalsByDate():Flow<List<Goal>>
    suspend fun deleteGaol(goal: Goal)
    suspend fun addGoal(goal: Goal)
    suspend fun getGoalById(id:Int):Goal?

}