package com.wagdev.tassyir.goal_feature.domain.goalUseCase

import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.goal_feature.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class AddEditGoalUseCase(
    val repository: GoalRepository
) {
    operator suspend fun invoke(goal : Goal ){
        return repository.addGoal(goal)
    }
}