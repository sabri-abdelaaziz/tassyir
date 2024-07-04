package com.wagdev.tassyir.goal_feature.presentation.goals

import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.note_feature.domain.model.Note

data class GoalState(
    var goals: List<Goal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val newGoalTitle: String = "",
    val newGoalDescription: String = ""
    )
