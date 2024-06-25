package com.wagdev.tassyir.note_feature.di

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wagdev.tassyir.note_feature.data.local.NoteDataBase
import com.wagdev.tassyir.note_feature.data.repository.NoteRepositoryImpl
import com.wagdev.tassyir.note_feature.domain.repository.NoteRepository
import com.wagdev.tassyir.note_feature.domain.usecases.AddNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.DeleteNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.GetNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.GetNotesUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application): NoteDataBase {
        return Room.databaseBuilder(
            app,
            NoteDataBase::class.java,
            NoteDataBase.DATABASE_NAME
        ).build()

    }

    @Provides
    @Singleton
    fun providesNoteRepository(
        db:NoteDataBase
    ): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )

    }

}