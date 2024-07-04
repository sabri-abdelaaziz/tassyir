package com.wagdev.tassyir.goal_feature.presentation.goals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.GetGoalUseCase
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.GoalUseCase
import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.goal_feature.domain.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class GoalViewModel @Inject constructor(
    val goalUseCase: GoalUseCase
):ViewModel(){
    private val _state = MutableStateFlow(GoalState())
    val state:StateFlow<GoalState> = _state

    val stateOfRangeDate= mutableStateOf(false)

    init {
        viewModelScope.launch {
            onEvent(GoalEvent.LoadGoals)
        }
    }

     fun onEvent(event:GoalEvent){
        when(event){
            is GoalEvent.DeleteGoal -> {
                viewModelScope.launch {
                      goalUseCase.deleteGoalUseCase(
                            event.goal
                        )
                    println(event.goal)

                }
            }
            is GoalEvent.LoadGoals -> {

                viewModelScope.launch {
                    goalUseCase.getGoalsUseCase().collect{ goals ->
                        _state.value = _state.value.copy(goals = goals)
                    }
                }

            }

            is GoalEvent.GoalsOrderByDate->{
                if(event.date){
                    stateOfRangeDate.value=true

                viewModelScope.launch {
                        goalUseCase.getGoalsByDateUc().collect{goals->
                            _state.value=_state.value.copy(goals = goals)
                        }
                    }



                }else{
                    stateOfRangeDate.value=false
                    viewModelScope.launch {

                        goalUseCase.getGoalsByTitleUc().collect{
                            _state.value=_state.value.copy(goals = it)
                        }
                    }
                }
            }
        }
    }
}