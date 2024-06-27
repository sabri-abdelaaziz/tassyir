package com.wagdev.tassyir.task_feature.presentation.addedittask

import androidx.compose.ui.focus.FocusState

sealed class AddEditTaskEvent {
    data class EnteredTitle(val value:String):AddEditTaskEvent()
    data class ChangeTitleFocusState(val focusState: FocusState):AddEditTaskEvent()
    data class EnteredContent(val value:String):AddEditTaskEvent()
    data class ChangeContentFocusState(val focusState: FocusState):AddEditTaskEvent()
    data class EnteredColor(val color:Int):AddEditTaskEvent()
    object SaveTask:AddEditTaskEvent()

}