package com.wagdev.tassyir.goal_feature.presentation.goals

import com.wagdev.tassyir.goal_feature.domain.model.Goal

sealed class GoalEvent {
    // when you use the class without param you can use object
    object LoadGoals : GoalEvent()
    // use data class when you need some parameters
   data class DeleteGoal(val goal: Goal) : GoalEvent()
    data class GoalsOrderByDate(val date: Boolean) : GoalEvent()
}
