package com.wagdev.tassyir.goal_feature.presentation.addEditGoal

import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.goal_feature.presentation.goals.GoalEvent

sealed class AddEditGoalEvent {
    data class AddGoal(val goal: Goal) : AddEditGoalEvent()
    data class EditTitle(val title:String) : AddEditGoalEvent()
    data class EditDeadline(val deadline:Long) : AddEditGoalEvent()


}