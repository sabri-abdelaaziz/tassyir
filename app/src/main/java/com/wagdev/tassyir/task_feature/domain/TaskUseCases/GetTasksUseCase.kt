package com.wagdev.tassyir.task_feature.domain.TaskUseCases

import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.TaskRepository.TaskRepository
import com.wagdev.tassyir.task_feature.domain.util.TaskOrder
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
    val repository: TaskRepository
) {
     operator fun invoke(taskOrder: TaskOrder):Flow<List<Task>>{
        return repository.getAllTasks()
    }
}