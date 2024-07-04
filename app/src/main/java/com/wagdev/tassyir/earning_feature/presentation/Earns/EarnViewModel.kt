package com.wagdev.tassyir.earning_feature.presentation.Earns

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.earning_feature.domain.model.Earn
import com.wagdev.tassyir.earning_feature.domain.useCases.EarnUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarnViewModel @Inject constructor(
    private val earnUseCase: EarnUseCases
): ViewModel() {
    private val _state:MutableStateFlow<List<Earn>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<Earn>> = _state

    var earn by mutableStateOf<Earn?>(null)
    val isModelOpen=mutableStateOf(false)



    init{
        viewModelScope.launch {
            earnUseCase.getEarnUseCase().collect{
                _state.value = it
            }
        }
    }

    suspend fun onEvent(event: EarnEvent){
      when(event){
          is EarnEvent.deleteEarn -> {
              viewModelScope.launch {
                  earnUseCase.deleteEarnUseCase(event.earn)
              }
          }
          is EarnEvent.getEarnById -> {
              viewModelScope.launch {
                 earn= earnUseCase.getEarnByIDUseCase(event.id)
              }
          }
          is EarnEvent.getEarns -> {
              viewModelScope.launch {
                  earnUseCase.getEarnUseCase().collect {
                      _state.value = it
                  }
              }

          }
          is EarnEvent.insertEarn -> {
              viewModelScope.launch {
                  earnUseCase.insertEarnUseCase(event.earn)
              }

          }
          is EarnEvent.updateEarn -> {
              viewModelScope.launch {
                  earnUseCase.insertEarnUseCase(event.earn)
              }
          }
      }

    }
}