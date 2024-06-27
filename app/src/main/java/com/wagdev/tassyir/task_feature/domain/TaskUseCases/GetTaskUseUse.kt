package com.wagdev.tassyir.task_feature.domain.TaskUseCases

import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.TaskRepository.TaskRepository

class GetTaskUseUse(
    val repository: TaskRepository
){
    suspend operator fun  invoke(id:Int): Task? {
        return repository.getTaskById(id)
    }
}