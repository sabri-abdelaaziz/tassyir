package com.wagdev.tassyir.task_feature.domain.TaskUseCases

data class TaskUseCase(
    val getTasksUseUse: GetTasksUseCase,
    val getTaskUseUse: GetTaskUseUse,
    val deleteUseCase: DeleteTaskUseCase,
    val insertUseCase:AddEditTaskUseCase
)