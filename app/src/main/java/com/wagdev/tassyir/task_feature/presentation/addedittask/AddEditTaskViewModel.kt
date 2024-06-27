package com.wagdev.tassyir.task_feature.presentation.addedittask

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.note_feature.domain.model.InvalidNoteException
import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.note_feature.presentation.add_edit_note.NoteTextFieldState
import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.TaskUseCases.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// declare the class with the hilt injection
@HiltViewModel
class AddEditTaskViewModel  @Inject constructor(
    private val taskUseCases: TaskUseCase,
    savedStateHandle: SavedStateHandle
    ) : ViewModel() {
//observer the state of task elements
        private val _taskTitle = mutableStateOf(
            NoteTextFieldState(
                hint = "Enter Title ..."
            )
        )
        val taskTitle: State<NoteTextFieldState> = _taskTitle

        private val _taskContent = mutableStateOf(
            NoteTextFieldState(
            hint = "Enter Some content"
        )
        )
        val taskContent: State<NoteTextFieldState> = _taskContent

        private val _taskColor = mutableStateOf(Note.noteColors.random().toArgb())
        val noteColor: State<Int> = _taskColor

    // the Event Flow  to handle the events
        private val _eventFlow = MutableSharedFlow<UiEvent>()
        val eventFlow = _eventFlow.asSharedFlow()

    // the current id if we in the edit screen
        private var currentTaskId: Int? = null

    // the init function in view model is often used for initialisation of the variables

        init {
            // get the id  from the path navigation as State of the Activity
            savedStateHandle.get<Int>("taskId")?.let { taskId ->
                if (taskId != -1) {
                    viewModelScope.launch {
                        taskUseCases.getTaskUseUse(taskId)?.also { task ->
                            currentTaskId = task.id
                            _taskTitle.value = taskTitle.value.copy(
                                text = task.title,
                                isHintVisible = false
                            )
                            _taskContent.value = taskContent.value.copy(
                                text = task.description,
                                isHintVisible = false
                            )
                            _taskColor.value = task.color
                        }
                    }
                }
            }
        }
        // onEvent function is used for handle the event and take the decision about it of the type Task Event
    // to modify the state
        fun onEvent(event: AddEditTaskEvent) {
            when (event) {
                is AddEditTaskEvent.EnteredTitle -> {
                    _taskTitle.value = taskTitle.value.copy(
                        text = event.value
                    )
                }
                is AddEditTaskEvent.ChangeTitleFocusState -> {
                    _taskTitle.value = taskTitle.value.copy(
                        isHintVisible = !event.focusState.isFocused && taskTitle.value.text.isBlank()
                    )
                }
                is AddEditTaskEvent.EnteredContent -> {
                    _taskContent.value = taskContent.value.copy(
                        text = event.value
                    )
                }
                is AddEditTaskEvent.ChangeContentFocusState -> {
                    _taskContent.value = taskContent.value.copy(
                        isHintVisible = !event.focusState.isFocused && taskContent.value.text.isBlank()
                    )
                }
                is AddEditTaskEvent.EnteredColor -> {
                    _taskColor.value = event.color
                }
                is AddEditTaskEvent.SaveTask -> {
                    viewModelScope.launch {
                        try {
                            taskUseCases.insertUseCase(
                                Task(
                                    title = taskTitle.value.text,
                                    description = taskContent.value.text,
                                    dueDate =  System.currentTimeMillis(),
                                    color = noteColor.value,
                                    id = currentTaskId
                                )
                            )
                            _eventFlow.emit(UiEvent.SaveNote)
                        } catch (e: InvalidNoteException) {
                            _eventFlow.emit(UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save note"
                            ))
                        }
                    }
                }
            }
        }

        sealed class UiEvent {
            data class ShowSnackBar(val message: String) : UiEvent()
            object SaveNote : UiEvent()
        }
    }

