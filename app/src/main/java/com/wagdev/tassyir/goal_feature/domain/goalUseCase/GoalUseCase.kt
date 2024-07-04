package com.wagdev.tassyir.goal_feature.domain.goalUseCase

data class GoalUseCase(
    val addEditGoalUseCase: AddEditGoalUseCase,
    val deleteGoalUseCase:DeleteGoalUseCase,
    val getGoalUseCase:GetGoalUseCase,
    val getGoalsUseCase:GetGoalsUseCase,
    val getGoalsByDateUc:GoalOrderByDate,
    val getGoalsByTitleUc:GoalsOrderByTitle,
)