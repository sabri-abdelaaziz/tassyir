package com.wagdev.tassyir.goal_feature.presentation.goals

import android.annotation.SuppressLint
import android.provider.MediaStore.Audio.Radio
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wagdev.tassyir.goal_feature.domain.model.Goal


@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GoalScreen(
   navController: NavController,
    modifier: Modifier = Modifier,
    viewModel:GoalViewModel= hiltViewModel()
) {
    val goals= viewModel.state.collectAsState()
    viewModel.onEvent(GoalEvent.LoadGoals)
    val stateOrderDate=viewModel.stateOfRangeDate.value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_edit_goal") }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(20.dp)),contentAlignment = Alignment.Center){
                Row(modifier=Modifier.fillMaxWidth().align(Alignment.TopCenter), horizontalArrangement = Arrangement.SpaceEvenly){
                    Row() {
                        Text(text = "Data")
                        RadioButton(selected = stateOrderDate, onClick = {
                            viewModel.onEvent(GoalEvent.GoalsOrderByDate(true))
                        })
                    }
                    Row() {
                        Text(text = "Title")
                        RadioButton(selected = !stateOrderDate, onClick = {
                            viewModel.onEvent(GoalEvent.GoalsOrderByDate(false))
                        })
                    }

                }
                LazyColumn {
                    itemsIndexed(goals.value.goals){i,goal->
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("add_edit_goal?goalId=${goal.id}")
                            }){
                            Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                                Text(text=goal.title.toString())
                                Text(text=goal.deadline.toString())
                                Text(text=goal.id.toString())
                                IconButton(onClick = {
                                    viewModel.onEvent(GoalEvent.DeleteGoal(goal))
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = null)
                                }
                            }

                        }

                    }
                }
            }


        }
    )





}
