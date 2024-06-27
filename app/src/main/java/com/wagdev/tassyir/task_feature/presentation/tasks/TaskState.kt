package com.wagdev.tassyir.task_feature.presentation.tasks

import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.util.TaskOrder
import com.wagdev.tassyir.task_feature.domain.util.TaskOrderType

data class TaskState(
    val tasks:List<Task> = emptyList(),
    val taskOrder: TaskOrder = TaskOrder.Date(TaskOrderType.Descending),
    val isOrderSectionVisible:Boolean=true

)