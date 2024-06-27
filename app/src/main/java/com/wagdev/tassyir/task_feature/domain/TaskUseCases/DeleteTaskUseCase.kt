package com.wagdev.tassyir.task_feature.domain.TaskUseCases

import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.TaskRepository.TaskRepository

class DeleteTaskUseCase(
    val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task){
        repository.deleteTask(task)
    }
}