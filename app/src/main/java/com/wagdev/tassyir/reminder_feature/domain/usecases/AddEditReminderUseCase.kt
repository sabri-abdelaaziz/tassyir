package com.wagdev.tassyir.reminder_feature.domain.usecases

import com.wagdev.tassyir.reminder_feature.domain.model.Reminder
import com.wagdev.tassyir.reminder_feature.domain.repository.ReminderRepository

class AddEditReminderUseCase (
    private val remiderRepository: ReminderRepository
){
    suspend operator fun invoke(reminder: Reminder){
        remiderRepository.addEditReminder(reminder)
    }
}