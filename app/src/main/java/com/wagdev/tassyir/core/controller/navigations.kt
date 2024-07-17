package com.wagdev.tassyir.core.controller

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wagdev.tassyir.core.presentation.common_ui.Activities
import com.wagdev.tassyir.core.util.BottomItem
import com.wagdev.tassyir.core.presentation.common_ui.Home
import com.wagdev.tassyir.core.presentation.common_ui.Schedule
import com.wagdev.tassyir.core.presentation.common_ui.Splash
import com.wagdev.tassyir.note_feature.presentation.add_edit_note.AddEditNoteScreen
import com.wagdev.tassyir.note_feature.presentation.notes.NoteScreen
import com.wagdev.tassyir.core.util.Screen
import com.wagdev.tassyir.earning_feature.presentation.Earns.EarnScreen
import com.wagdev.tassyir.goal_feature.presentation.addEditGoal.AddEditGoalScreen
import com.wagdev.tassyir.goal_feature.presentation.goals.GoalScreen
import com.wagdev.tassyir.reminder_feature.presentation.ReminderScreen
import com.wagdev.tassyir.spending_feature.presentation.spends.SpendScreen
import com.wagdev.tassyir.task_feature.presentation.addedittask.AddEditTaskScreen
import com.wagdev.tassyir.task_feature.presentation.tasks.TaskScreen

@Composable
fun Navigations(
    modifier: Modifier = Modifier,
    navController: NavHostController
)
{

    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.NotesScreen.route){
            NoteScreen(navController = navController)
        }

        composable(Screen.Schedule.route){
            Schedule(navController=navController)
        }
        composable(Screen.Activities.route){
            Activities(navController=navController)
        }
        composable(Screen.Splash.route){
            Splash(navController = navController)
        }
        composable(Screen.TaskScreen.route){
            TaskScreen(navController = navController)
        }

       //spending screen
        composable(Screen.Spending.route){
            SpendScreen(navController = navController, modifier = modifier)
        }
        // earning screen
        composable(Screen.Earning.route){
            EarnScreen(navController = navController)
        }
        //reminder screen
        composable(Screen.Reminder.route){
            ReminderScreen(navController=navController)
        }
        //addNote screen
        composable(
            Screen.AddEditNotesScreen.route+"?noteId={noteId}&noteColor={noteColor}", arguments = listOf(
                navArgument(name="noteId"){
                    type= NavType.IntType
                    defaultValue=-1
                },
                navArgument(name="noteColor"){
                    type= NavType.IntType
                    defaultValue=-1
                }
            )){
            val color=it.arguments?.getInt("noteColor")?:-1
            AddEditNoteScreen(navController = navController, noteColor =color )
        }

        composable(
            Screen.AddEditTaskScreen.route+"?taskId={taskId}&taskColor={taskColor}", arguments = listOf(
                navArgument(name="taskId"){
                    type= NavType.IntType
                    defaultValue=-1
                },
                navArgument(name="taskColor"){
                    type= NavType.IntType
                    defaultValue=-1
                }
            )){
            val color=it.arguments?.getInt("taskColor")?:-1
            AddEditTaskScreen(navController = navController, taskColor =color)
        }
        composable(Screen.Splash.route){
            Splash(navController = navController)
        }
        composable(Screen.Goals.route){
            GoalScreen(navController = navController)
        }
        composable(Screen.AddEditGoal.route, arguments = listOf(
            navArgument(name="goalId"){
                type=NavType.IntType
                defaultValue=-1
            }
        )){
            AddEditGoalScreen(navController = navController)
        }
        composable(Screen.Home.route){
            val itemButton=listOf(
                BottomItem(
                    title ="Home",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    badgeNum = 0,
                    hasBadge = false
                ),
                BottomItem(
                        title ="Notes",
                selectedIcon = Icons.Filled.EditNote,
                unselectedIcon = Icons.Outlined.EditNote,
                badgeNum = 0,
                hasBadge = false
            ),
            BottomItem(
                title ="Tasks",
                selectedIcon = Icons.Filled.Schedule,
                unselectedIcon = Icons.Outlined.Schedule,
                badgeNum = 0,
                hasBadge = false
            ),


                BottomItem(
                    title ="Finance",
                    selectedIcon = Icons.Filled.Money,
                    unselectedIcon = Icons.Outlined.Money,
                    badgeNum = 2,
                    hasBadge = true
                )

            )
            Home(
                bottomItems = itemButton,
                navController=navController)
        }

    }

}