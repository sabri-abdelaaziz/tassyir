package com.wagdev.tassyir.core.di

import android.app.Application
import androidx.room.Room
import com.wagdev.tassyir.note_feature.data.local.NoteDataBase
import com.wagdev.tassyir.note_feature.data.repository.NoteRepositoryImpl
import com.wagdev.tassyir.note_feature.domain.repository.NoteRepository
import com.wagdev.tassyir.note_feature.domain.usecases.AddNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.DeleteNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.GetNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.GetNotesUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.NoteUseCases
import com.wagdev.tassyir.task_feature.data.repository.TaskRepositoryImp
import com.wagdev.tassyir.task_feature.domain.TaskRepository.TaskRepository
import com.wagdev.tassyir.task_feature.domain.TaskUseCases.AddEditTaskUseCase
import com.wagdev.tassyir.task_feature.domain.TaskUseCases.DeleteTaskUseCase
import com.wagdev.tassyir.task_feature.domain.TaskUseCases.GetTaskUseUse
import com.wagdev.tassyir.task_feature.domain.TaskUseCases.GetTasksUseCase
import com.wagdev.tassyir.task_feature.domain.TaskUseCases.TaskUseCase
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

    @Provides
    @Singleton
    fun providesTaskRepository(
        db:NoteDataBase
    ): TaskRepository {
        return TaskRepositoryImp(db.taskDao)
    }

    @Provides
    @Singleton
    fun providesTaskUseCases(repository: TaskRepository):TaskUseCase {
        return TaskUseCase(
            getTaskUseUse = GetTaskUseUse(repository),
            deleteUseCase = DeleteTaskUseCase(repository),
            insertUseCase = AddEditTaskUseCase(repository),
            getTasksUseUse = GetTasksUseCase(repository)
        )

    }

}