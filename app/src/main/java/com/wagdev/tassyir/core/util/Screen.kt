package com.wagdev.tassyir.core.util

sealed class Screen(
    val route:String
) {
    object NotesScreen: Screen("notes_screen")
    object AddEditNotesScreen: Screen("add_edit_note_screen")
    object Splash: Screen( "splash")
    object Earning: Screen( "earning")
    object Home: Screen("home")
    object Reminder:Screen("reminder")
    object Goals: Screen("goals")
    object AddEditGoal: Screen("add_edit_goal")
    object Schedule: Screen("schedule")
    object Spending: Screen("spending")
    object Activities: Screen("activities")
    object TaskScreen: Screen("task_screen")
    object AddEditTaskScreen: Screen("add_edit_task_screen")

}