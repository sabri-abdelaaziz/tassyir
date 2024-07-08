package com.wagdev.tassyir.reminder_feature.data.repository

import com.wagdev.tassyir.reminder_feature.data.db.ReminderDao
import com.wagdev.tassyir.reminder_feature.domain.model.Reminder
import com.wagdev.tassyir.reminder_feature.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderRepositoryImpl(
    private val reminderDao:ReminderDao
) :ReminderRepository{
    override suspend fun getAllReminders(): Flow<List<Reminder>> {
        return reminderDao.getReminders()
    }

    override suspend fun getReminderById(id: Int): Reminder? {
        return reminderDao.getReminderById(id)
    }

    override suspend fun addEditReminder(reminder: Reminder) {
        reminderDao.addEditReminder(reminder)
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(reminder)
    }

    override suspend fun searchReminders(query: String): List<Reminder> {
       return reminderDao.searchReminders(query)
    }

}