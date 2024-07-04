package com.wagdev.tassyir.goal_feature.domain.goalUseCase

import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.goal_feature.domain.repository.GoalRepository

class GetGoalUseCase(
    val repository: GoalRepository
) {
    operator suspend fun invoke(id:Int): Goal?{
        return repository.getGoalById(id)
    }
}