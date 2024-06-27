package com.wagdev.tassyir.note_feature.presentation.util

sealed class Screen(
    val route:String
) {
    object NotesScreen:Screen("notes_screen")
    object AddEditNotesScreen:Screen("add_edit_note_screen")
    object Splash:Screen( "splash")
    object Home:Screen("home")
    object Schedule:Screen("schedule")
    object Expenses:Screen("expenses")
    object Activities:Screen("activities")
    object TaskScreen:Screen("task_screen")
    object AddEditTaskScreen:Screen("add_edit_task_screen")

}