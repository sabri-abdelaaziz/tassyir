package com.wagdev.tassyir.goal_feature.domain.goalUseCase

import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.goal_feature.domain.repository.GoalRepository

class DeleteGoalUseCase(
    val repository: GoalRepository
) {
    operator suspend fun invoke(goal: Goal){
        repository.deleteGaol(goal)
    }
}