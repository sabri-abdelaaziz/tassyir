package com.wagdev.tassyir.goal_feature.domain.goalUseCase

import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.goal_feature.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class GetGoalsUseCase(
    val repository: GoalRepository
)  {
    operator fun invoke(): Flow<List<Goal>>{
        return repository.getGoals()
    }
}