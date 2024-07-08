package com.wagdev.tassyir.reminder_feature.domain.repository

import com.wagdev.tassyir.reminder_feature.data.db.ReminderDao
import com.wagdev.tassyir.reminder_feature.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository{
    suspend fun getAllReminders(): Flow<List<Reminder>>
    suspend fun getReminderById(id: Int): Reminder?
    suspend fun addEditReminder(reminder: Reminder)
    suspend fun deleteReminder(reminder: Reminder)
    suspend fun searchReminders(query: String): List<Reminder>
}