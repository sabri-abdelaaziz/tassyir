package com.wagdev.tassyir.task_feature.presentation.tasks


import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.util.TaskOrder


sealed class TaskEvent {
    data class Order(val taskOrder: TaskOrder):TaskEvent()
    data class DeleteTask(val task: Task): TaskEvent()
    object RestoreTask:TaskEvent()
    object ToggleOrderSection :TaskEvent()
}