package com.wagdev.tassyir.core.controller

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Note
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wagdev.tassyir.core.presentation.common_ui.Activities
import com.wagdev.tassyir.core.presentation.common_ui.BottomItem
import com.wagdev.tassyir.core.presentation.common_ui.Expenses
import com.wagdev.tassyir.core.presentation.common_ui.Home
import com.wagdev.tassyir.core.presentation.common_ui.Schedule
import com.wagdev.tassyir.core.presentation.common_ui.Splash
import com.wagdev.tassyir.note_feature.presentation.add_edit_note.AddEditNoteScreen
import com.wagdev.tassyir.note_feature.presentation.notes.NoteScreen
import com.wagdev.tassyir.note_feature.presentation.util.Screen

@Composable
fun Navigations(
    modifier: Modifier = Modifier,
    navController: NavHostController)
{

    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(Screen.NotesScreen.route){
            NoteScreen(navController = navController)
        }
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
        composable(Screen.Splash.route){
            Splash(navController = navController)
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
                selectedIcon = Icons.Filled.Note,
                unselectedIcon = Icons.Outlined.Note,
                badgeNum = 0,
                hasBadge = false
            ),
            BottomItem(
                title ="Planing",
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
        composable(Screen.Expenses.route){
            Expenses(navController=navController)
        }
        composable(Screen.Schedule.route){
            Schedule(navController=navController)
        }
        composable(Screen.Activities.route){
            Activities(navController=navController)
        }
    }

}