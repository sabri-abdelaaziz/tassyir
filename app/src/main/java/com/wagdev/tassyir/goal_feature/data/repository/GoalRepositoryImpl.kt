package com.wagdev.tassyir.goal_feature.data.repository

import com.wagdev.tassyir.goal_feature.data.db.GoalDao
import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.goal_feature.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class GoalRepositoryImpl(
    val goalDao: GoalDao
):GoalRepository {
    override fun getGoals(): Flow<List<Goal>> {
        return  goalDao.getAllGoals()
    }

    override fun getGoalsByTitle(): Flow<List<Goal>> {
        return goalDao.getGoalsByTitle()
    }

    override fun getGoalsByDate(): Flow<List<Goal>> {
        return goalDao.getGoalsByDate()
    }

    override suspend fun deleteGaol(goal: Goal) {
        goalDao.deleteGoal(goal)
    }

    override suspend fun addGoal(goal: Goal) {
        goalDao.addGoal(goal)
    }

    override suspend fun getGoalById(id: Int):Goal? {
        return goalDao.getGoalById(id)
    }

}