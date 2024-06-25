package com.wagdev.tassyir.note_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wagdev.tassyir.note_feature.domain.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {
    abstract val noteDao : NoteDao
    companion object{
        const val DATABASE_NAME="tassyir_db"
    }
}