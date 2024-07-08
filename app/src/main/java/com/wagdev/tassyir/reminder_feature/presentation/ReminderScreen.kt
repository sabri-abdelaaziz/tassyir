package com.wagdev.tassyir.reminder_feature.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DisabledByDefault
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
    "StateFlowValueCalledInComposition"
)
@Composable
fun ReminderScreen(
    navController: NavController,
    viewModel: ReminderViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.status.collectAsState().value
    val message = mutableStateOf("")
    val date = mutableLongStateOf(0)

    Scaffold(
        content = {
            Column(modifier = Modifier.padding(PaddingValues(3.dp))) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.reminders) { e ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(e.message)
                            Text(e.date.toString())
                            IconButton(onClick = {
                                viewModel.onEvent(ReminderEvent.deleteReminder(e))
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }

                if (state.isLoading) {
                    AlertDialog(
                        onDismissRequest = {
                            viewModel.onEvent(ReminderEvent.ToggleLoading(false))
                        },
                        confirmButton = {
                            Button(onClick = {
                                state.reminder?.let { reminder ->
                                    viewModel.onEvent(ReminderEvent.addEditReminder(reminder))
                                    viewModel.onEvent(ReminderEvent.ToggleLoading(false))
                                }
                            }) {
                                Text("Confirm")
                            }
                        },
                        dismissButton = {

                            Button(onClick = {
                                viewModel.onEvent(ReminderEvent.ToggleLoading(false))
                            }) {
                                Text("Dismiss")
                            }
                        },
                        title = {
                            Text("Add new Reminder Note")
                        },
                        text = {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Message")
                                TextField(
                                    value =message.value,
                                    onValueChange = {
                                        message.value=it
                                        viewModel.onEvent(ReminderEvent.insertMessage(message.value))
                                    }
                                )
                                Text("Date")
                                TextField(
                                    value = date.longValue.toString(),
                                    onValueChange = {
                                        date.longValue = it.toLong()
                                        viewModel.onEvent(ReminderEvent.chooseDate(date.longValue))
                                    }
                                )
                            }
                        }
                    )
                }
            }
        },
        topBar = {},
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ReminderEvent.ToggleLoading(true))
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        bottomBar = {}
    )
}
