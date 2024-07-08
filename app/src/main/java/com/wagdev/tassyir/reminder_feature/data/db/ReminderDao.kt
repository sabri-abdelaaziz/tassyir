package com.wagdev.tassyir.reminder_feature.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.wagdev.tassyir.reminder_feature.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Upsert
    suspend fun addEditReminder(reminder:Reminder)
    @Delete
    suspend fun deleteReminder(reminder:Reminder)
    @Query("SELECT * FROM reminder")
    fun getReminders(): Flow<List<Reminder>>
    @Query("SELECT * FROM reminder WHERE id=:id")
    suspend fun getReminderById(id:Int):Reminder?
    @Query("SELECT * FROM reminder WHERE date=:date")
    suspend fun getRemindersByDate(date:Long):List<Reminder>
    @Query("SELECT * FROM reminder WHERE message LIKE '%' || :query || '%'")
    suspend fun searchReminders(query:String):List<Reminder>



}