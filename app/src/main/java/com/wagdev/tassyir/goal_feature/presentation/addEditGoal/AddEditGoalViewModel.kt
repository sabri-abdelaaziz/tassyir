package com.wagdev.tassyir.goal_feature.presentation.addEditGoal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.AddEditGoalUseCase
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.GoalUseCase
import com.wagdev.tassyir.goal_feature.domain.model.Goal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditGoalViewModel @Inject constructor(
     val goalUseCase: GoalUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var title by mutableStateOf("")
    var deadline by mutableStateOf(System.currentTimeMillis())
    private val _state= MutableStateFlow(
        Goal(
            id = 0,
            title = "",
            deadline = System.currentTimeMillis()+10000
       )
    )
    var currentGoalId by mutableStateOf(0)

    val state:StateFlow<Goal> =_state
    init {
        savedStateHandle.get<Int>("GoalId")?.let { goalId ->
            if (goalId != -1) {
                viewModelScope.launch {
                    goalUseCase.getGoalUseCase(goalId)?.also { goal->
                        currentGoalId = goal.id
                        title = goal.title
                        deadline = goal.deadline
                        _state.value= _state.value.copy(
                            id=goal.id,
                            deadline = goal.deadline,
                            title = goal.title

                        )

                    }
                }
            }
        }
    }

    fun onEvent(event:AddEditGoalEvent){
        when(event){
            is AddEditGoalEvent.AddGoal -> {
                viewModelScope.launch {
                    goalUseCase.addEditGoalUseCase(
                        _state.value.copy(
                            id = currentGoalId,
                            title = title,
                            deadline = deadline
                        ))  

                }

            }
            is AddEditGoalEvent.EditTitle ->{
                title=event.title
                _state.value=_state.value.copy(
                    title =title
                )
            }
            is AddEditGoalEvent.EditDeadline ->{
               deadline=event.deadline
                _state.value=_state.value.copy(
                    deadline = deadline
                )
            }

        }
    }
}