package com.wagdev.tassyir.note_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.task_feature.data.local.TaskDao
import com.wagdev.tassyir.task_feature.domain.Model.Task


@Database(entities = [Note::class, Task::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {
    abstract val noteDao : NoteDao
    abstract val taskDao: TaskDao
    companion object{
        const val DATABASE_NAME="tassyir_d"
    }
}