package com.wagdev.tassyir.reminder_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.reminder_feature.domain.usecases.ReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderUseCases: ReminderUseCase
) :ViewModel(){

    private val _status = MutableStateFlow(ReminderStatus())
    val status: StateFlow<ReminderStatus> = _status
    init{
        onEvent(
            ReminderEvent.getAllReminders
        )
    }

    fun onEvent(event:ReminderEvent){
        when(event){
            is ReminderEvent.ToggleLoading -> {
                _status.value = _status.value.copy(isLoading = event.isLoading)
            }
            is ReminderEvent.insertMessage->{
                viewModelScope.launch{
                    _status.value = _status.value.copy(
                        reminder = _status.value.reminder?.copy(message = event.message)
                    )
                }
            }
            is ReminderEvent.chooseDate -> {
                viewModelScope.launch{
                    _status.value=_status.value.copy(
                        reminder=_status.value.reminder?.copy(
                            date = event.date
                        )
                    )
                }
            }
            is ReminderEvent.getAllReminders ->{
                viewModelScope.launch{
                    reminderUseCases.getAllRemindersUseCase().collect{
                        _status.value=status.value.copy(
                         reminders = it
                        )
                    }
                }

            }
            is ReminderEvent.getReminderById ->{
                viewModelScope.launch{
                    _status.value=status.value.copy(
                        reminder=reminderUseCases.getReminderByIdUseCase(event.id)
                    )
                }

            }
            is ReminderEvent.addEditReminder ->{
                viewModelScope.launch{
                    reminderUseCases.addEditReminderUseCase(event.reminder)
                }

            }
            is ReminderEvent.deleteReminder ->{
                viewModelScope.launch {
                    reminderUseCases.deleteReminderUseCase(event.reminder)
                }
            }
        }
    }

}