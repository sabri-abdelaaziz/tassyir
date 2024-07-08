package com.wagdev.tassyir.core.di

import android.app.Application
import androidx.room.Room
import com.wagdev.tassyir.core.NoteDataBase
import com.wagdev.tassyir.earning_feature.data.repository.EarnRepositoryImpl
import com.wagdev.tassyir.earning_feature.domain.repository.EarnRepository
import com.wagdev.tassyir.earning_feature.domain.useCases.AddEditEarnUseCase
import com.wagdev.tassyir.earning_feature.domain.useCases.DeleteEarnUseCase
import com.wagdev.tassyir.earning_feature.domain.useCases.EarnUseCases
import com.wagdev.tassyir.earning_feature.domain.useCases.GetEarnByIDUseCase
import com.wagdev.tassyir.earning_feature.domain.useCases.GetEarnsUseCase
import com.wagdev.tassyir.goal_feature.data.repository.GoalRepositoryImpl
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.AddEditGoalUseCase
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.DeleteGoalUseCase
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.GetGoalUseCase
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.GetGoalsUseCase
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.GoalOrderByDate
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.GoalUseCase
import com.wagdev.tassyir.goal_feature.domain.goalUseCase.GoalsOrderByTitle
import com.wagdev.tassyir.goal_feature.domain.repository.GoalRepository
import com.wagdev.tassyir.note_feature.data.repository.NoteRepositoryImpl
import com.wagdev.tassyir.note_feature.domain.repository.NoteRepository
import com.wagdev.tassyir.note_feature.domain.usecases.AddNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.DeleteNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.GetNoteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.GetNotesUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.NoteUseCases
import com.wagdev.tassyir.reminder_feature.data.repository.ReminderRepositoryImpl
import com.wagdev.tassyir.reminder_feature.domain.repository.ReminderRepository
import com.wagdev.tassyir.reminder_feature.domain.usecases.AddEditReminderUseCase
import com.wagdev.tassyir.reminder_feature.domain.usecases.DeleteReminderUseCase
import com.wagdev.tassyir.reminder_feature.domain.usecases.GetAllRemindersUseCase
import com.wagdev.tassyir.reminder_feature.domain.usecases.GetReminderByIdUseCase
import com.wagdev.tassyir.reminder_feature.domain.usecases.ReminderUseCase
import com.wagdev.tassyir.spending_feature.data.repository.SpendRepositoryImpl
import com.wagdev.tassyir.spending_feature.domain.repository.SpendRepository
import com.wagdev.tassyir.spending_feature.domain.usecases.AddEditSpendUseCase
import com.wagdev.tassyir.spending_feature.domain.usecases.DeleteSpendUseCase
import com.wagdev.tassyir.spending_feature.domain.usecases.GetSpendByIdUseCase
import com.wagdev.tassyir.spending_feature.domain.usecases.GetSpendsUseCase
import com.wagdev.tassyir.spending_feature.domain.usecases.SpendUseCase
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
        db: NoteDataBase
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
        db: NoteDataBase
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
    @Provides
    @Singleton
    fun providesGoalRepository(
        db: NoteDataBase
    ): GoalRepository {
        return GoalRepositoryImpl(db.goalDao)
    }

    @Provides
    @Singleton
    fun providesGoalUseCases(repository: GoalRepository):GoalUseCase {
        return GoalUseCase(
            getGoalsByTitleUc =  GoalsOrderByTitle(repository),
            getGoalUseCase = GetGoalUseCase(repository),
            getGoalsUseCase = GetGoalsUseCase(repository),
            getGoalsByDateUc= GoalOrderByDate(repository) ,
            deleteGoalUseCase = DeleteGoalUseCase(repository),
            addEditGoalUseCase = AddEditGoalUseCase(repository)
        )

    }

    /// Provides for testing the use cases of earning feature
    @Provides
    @Singleton
    fun providesEarnRepository(
        db: NoteDataBase
    ): EarnRepository {
        return EarnRepositoryImpl(db.earnDao)
    }

    @Provides
    @Singleton
    fun providesEarnUseCases(repository: EarnRepository):EarnUseCases{
        return EarnUseCases(
            getEarnUseCase = GetEarnsUseCase(repository),
            getEarnByIDUseCase = GetEarnByIDUseCase(repository),
            deleteEarnUseCase = DeleteEarnUseCase(repository),
            insertEarnUseCase = AddEditEarnUseCase(repository)
        )

    }
    /// Provides for testing the use cases of spending feature

  @Provides
  @Singleton
  fun providesSpendRepository(
      db:NoteDataBase
  ):SpendRepository{
      return SpendRepositoryImpl(db.spendDao)
  }

    @Provides
    @Singleton
    fun providesSpendUseCases(
        repository:SpendRepository
    ):SpendUseCase{
        return SpendUseCase(
            getSpendByIdUseCase = GetSpendByIdUseCase(repository),
            getSpendsUseCase = GetSpendsUseCase(repository),
            deleteSpendUseCase = DeleteSpendUseCase(repository),
            addEditeSpend = AddEditSpendUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun providesReminderRepository(
        db:NoteDataBase
    ):ReminderRepository{
        return ReminderRepositoryImpl(db.reminderDao)
    }

    @Provides
    @Singleton
    fun providesReminderUseCases(
        repository:ReminderRepository
    ):ReminderUseCase{
        return ReminderUseCase(
            getAllRemindersUseCase = GetAllRemindersUseCase(repository),
            getReminderByIdUseCase = GetReminderByIdUseCase(repository),
            deleteReminderUseCase = DeleteReminderUseCase(repository),
            addEditReminderUseCase = AddEditReminderUseCase(repository)
        )
    }

}