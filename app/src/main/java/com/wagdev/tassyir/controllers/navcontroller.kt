package com.wagdev.tassyir.controllers

package com.wagdev.tasiyyir.controllers

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wagdev.tassiyir.notes_feauture.presentation.Detail.DetailScreen
import com.wagdev.tassiyir.notes_feauture.presentation.Detail.DetailedAssistedFactory
import com.wagdev.tassyir.note_feauture.presentation.bookmark.BookMarkScreen
import com.wagdev.tassyir.note_feauture.presentation.bookmark.BookMarkViewModel
import com.wagdev.tassyir.note_feauture.presentation.home.HomeScreen
import com.wagdev.tassyir.note_feauture.presentation.home.HomeViewModel
import com.wagdev.tassyir.presentation.*

@SuppressLint("SuspiciousIndentation")
@Composable
fun NavCont(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    bookMarkViewModel: BookMarkViewModel,
    assistedFactory: DetailedAssistedFactory?

) {
    //val navController= rememberNavController()
    NavHost(navController = navController as NavHostController, startDestination = "note"){
        composable("splash"){
            Splash(navController = navController)
        }
        composable("home"){
            Home(navController=navController)
        }
        composable("expenses"){
            Expenses(navController=navController)
        }
        composable("schedule"){
            Schedule(navController=navController)
        }
        composable("activities"){
            Activities(navController=navController)
        }
        composable("note"){

            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state =   state  ,
                onBookMarkChange = homeViewModel::onBookMarkChange,
                onDeletedNote =homeViewModel::deleteNote,
                onNoteClicked ={
                    navController.navigateToSingleTop("detail?id=${it}")
                }
            )
        }
        composable("bookmark"){
            val state by bookMarkViewModel.state.collectAsState()
            BookMarkScreen(
                state =state ,
                onBookMarkChange = bookMarkViewModel::onBookMarkChange,
                onNoteClicked ={
                    navController.navigateToSingleTop("detail?id=${it}")
                } ,
                onDeletedNote =bookMarkViewModel::onDeleteNote
            )
        }



        composable(
            route="detail?id={id}",
            arguments = listOf(navArgument("id"){
                NavType.LongType
                defaultValue=-1L
            })
        ){backStackEntry ->
            val id =backStackEntry.arguments?.getLong("id")?:-1L
            if (assistedFactory != null) {
                DetailScreen(
                    noteId = id,
                    assistedFactory = assistedFactory,
                    navigatUp = {
                        navController.navigateUp()
                    }
                )
            }

        }
    }

}

fun NavHostController.navigateToSingleTop(route:String){
    navigate(route){
        popUpTo(graph.findStartDestination().id){
            saveState=true
        }
        launchSingleTop=true
        restoreState=true
    }
}