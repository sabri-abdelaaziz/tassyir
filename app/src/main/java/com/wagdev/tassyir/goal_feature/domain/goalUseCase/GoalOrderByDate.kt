package com.wagdev.tassyir.goal_feature.domain.goalUseCase

import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.goal_feature.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class GoalOrderByDate(
    val goalRepository: GoalRepository
) {
    operator suspend fun invoke(): Flow<List<Goal>> {
        return goalRepository.getGoalsByDate()
    }

}