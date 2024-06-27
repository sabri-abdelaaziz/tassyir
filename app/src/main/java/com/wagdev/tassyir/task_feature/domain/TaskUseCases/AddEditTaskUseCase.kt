package com.wagdev.tassyir.task_feature.domain.TaskUseCases

import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.TaskRepository.TaskRepository

class AddEditTaskUseCase (
    val repository: TaskRepository
){
    suspend operator fun invoke(task: Task){
        repository.insertTask(task = task)
    }
}