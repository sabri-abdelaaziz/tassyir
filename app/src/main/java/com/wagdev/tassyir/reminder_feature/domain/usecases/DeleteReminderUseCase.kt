package com.wagdev.tassyir.reminder_feature.domain.usecases

import com.wagdev.tassyir.reminder_feature.domain.model.Reminder
import com.wagdev.tassyir.reminder_feature.domain.repository.ReminderRepository

class DeleteReminderUseCase(
    private val reminderRepository: ReminderRepository
) {
    suspend operator fun invoke(reminder: Reminder) {
        reminderRepository.deleteReminder(reminder)
    }
}