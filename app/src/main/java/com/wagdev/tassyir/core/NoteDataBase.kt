package com.wagdev.tassyir.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wagdev.tassyir.earning_feature.data.local.EarnDao
import com.wagdev.tassyir.earning_feature.domain.model.Earn
import com.wagdev.tassyir.goal_feature.data.db.GoalDao
import com.wagdev.tassyir.goal_feature.domain.model.Goal
import com.wagdev.tassyir.note_feature.data.local.NoteDao
import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.reminder_feature.data.db.ReminderDao
import com.wagdev.tassyir.reminder_feature.domain.model.Reminder
import com.wagdev.tassyir.spending_feature.data.db.SpendDao
import com.wagdev.tassyir.spending_feature.domain.model.Spend
import com.wagdev.tassyir.task_feature.data.local.TaskDao
import com.wagdev.tassyir.task_feature.domain.Model.Task


@Database(entities = [Note::class, Task::class, Goal::class, Earn::class, Spend::class, Reminder::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {
    abstract val noteDao : NoteDao
    abstract val taskDao: TaskDao
    abstract  val goalDao:GoalDao
    abstract val earnDao:EarnDao
    abstract val spendDao: SpendDao
    abstract val reminderDao: ReminderDao

    companion object{
        const val DATABASE_NAME="tassyir_ddbaaya"
    }
}