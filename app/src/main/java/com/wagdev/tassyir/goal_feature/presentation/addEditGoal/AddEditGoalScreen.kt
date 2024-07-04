package com.wagdev.tassyir.goal_feature.presentation.addEditGoal

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.wagdev.tassyir.core.util.Screen
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddEditGoalScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AddEditGoalViewModel= hiltViewModel()
) {
    val goal= viewModel.state.collectAsState()

    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        TextField(value = goal.value.title, onValueChange = {
            viewModel.onEvent(AddEditGoalEvent.EditTitle(it))
        })
        TextField(value = goal.value.deadline.toString(), onValueChange = {
            viewModel.onEvent(AddEditGoalEvent.EditDeadline(it.toLong()))
        })
        IconButton(onClick = { /*TODO*/ }) {
            IconButton(onClick = {
                viewModel.onEvent(AddEditGoalEvent.AddGoal(goal.value))
                navController.navigate(Screen.Goals.route)
            }) {
               Icon(Icons.Default.Add,contentDescription=null)
            }

        }



    }
}