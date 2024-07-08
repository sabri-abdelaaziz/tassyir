package com.wagdev.tassyir.spending_feature.presentation.spends

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.note_feature.presentation.notes.NoteState
import com.wagdev.tassyir.spending_feature.domain.model.Spend
import com.wagdev.tassyir.spending_feature.domain.usecases.SpendUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpendViewModel @Inject constructor(
    private val spendUseCases: SpendUseCase
):ViewModel(){
    private val _state = MutableStateFlow(SpendState())
    val state: StateFlow<SpendState> = _state



    init{
        onEvent(SpendEvent.GetSpendList)
    }

    fun onEvent(event:SpendEvent){
        when(event){
            is SpendEvent.GetSpendList -> {
                viewModelScope.launch {
                    _state.value=_state.value.copy(
                        spedList = spendUseCases.getSpendsUseCase()
                    )
                }
            }
            is SpendEvent.AddEditSpend->{
                viewModelScope.launch {
                    spendUseCases.addEditeSpend(event.spend)
                }
            }
            is SpendEvent.DeleteSpend ->{

            }
            is SpendEvent.GetSpendById ->{

            }
            is SpendEvent.ChangeDialogStatus->{
                _state.value=_state.value.copy(
                    isLoading = !state.value.isLoading
                )
            }

        }

    }

}