package com.wagdev.tassyir.reminder_feature.domain.usecases

import com.wagdev.tassyir.reminder_feature.domain.model.Reminder
import com.wagdev.tassyir.reminder_feature.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class GetAllRemindersUseCase(
    private val reminderRepository:ReminderRepository
) {
    suspend operator fun invoke(): Flow<List<Reminder>> {
        return reminderRepository.getAllReminders()
    }
}