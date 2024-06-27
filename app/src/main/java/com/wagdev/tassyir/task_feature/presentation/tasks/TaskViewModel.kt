package com.wagdev.tassyir.task_feature.presentation.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.TaskUseCases.TaskUseCase
import com.wagdev.tassyir.task_feature.domain.util.TaskOrder
import com.wagdev.tassyir.task_feature.domain.util.TaskOrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases:TaskUseCase
): ViewModel() {
    private val _state = mutableStateOf<TaskState>(TaskState())
    val state: State<TaskState> = _state
    private var recentlyDeleted: Task? = null
    private var getNotesJob: Job? = null

    init {
        getTasks(TaskOrder.Date(TaskOrderType.Descending))
    }

    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                   taskUseCases.deleteUseCase(event.task)
                    recentlyDeleted = event.task
                }

            }

            is TaskEvent.Order -> {
                if (state.value.taskOrder::class == event.taskOrder::class && state.value.taskOrder.orderType == event.taskOrder.orderType) {
                    return
                }
                getTasks(event.taskOrder)
            }

            is TaskEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.insertUseCase(recentlyDeleted ?: return@launch)
                    recentlyDeleted = null
                }
            }

            is TaskEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            TaskEvent.RestoreTask -> TODO()
        }
    }

    fun getTasks(taskOrder: TaskOrder) {
        getNotesJob?.cancel()
        getNotesJob = taskUseCases.getTasksUseUse(taskOrder).onEach { tasks ->
            _state.value = state.value.copy(
                tasks = tasks, taskOrder = taskOrder
            )

        }.launchIn(viewModelScope)
    }

}