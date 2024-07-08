package com.wagdev.tassyir.earning_feature.presentation.Earns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wagdev.tassyir.earning_feature.presentation.Earns.util.RadioItem
import com.wagdev.tassyir.earning_feature.presentation.components.ClassificationComponent
import com.wagdev.tassyir.earning_feature.presentation.components.EarnItem

@Composable
fun EarnScreen(
    navController: NavController,
    viewModel: EarnViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var earnState by remember { mutableStateOf(viewModel.earn) }
    val state by viewModel.state.collectAsState()
    val itemsOrder = listOf(
        RadioItem("Date", EarnEvent.getEarns, 0),
        RadioItem("Title", EarnEvent.getEarns, 1),
        RadioItem("Amount", EarnEvent.getEarns, 2)
    )
    val selectedOrderState = viewModel.selectedOrderState
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        modifier = modifier
    ) {
        Column(modifier = modifier.padding(it)) {
            ClassificationComponent(
                itemOrder = itemsOrder,
                Selected = selectedOrderState.value,
                onClick = {
                    selectedOrderState.intValue = it.num
                    viewModel.onEvent(it.use)
                }
            )
            LazyColumn {
                itemsIndexed(state) { _, item ->
                    EarnItem(
                        earn = item,
                        modifier = Modifier.fillMaxWidth(),
                        onEdit = { earn ->
                            earnState = earn
                            name = earn.name
                            amount = earn.amount.toString()
                            showDialog = true
                        },
                        onDelete = {
                            viewModel.onEvent(EarnEvent.deleteEarn(it))
                        },
                        showEdit = {
                            showDialog = true
                        }
                    )
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Enter Details") },
                    text = {
                        Column {
                            TextField(
                                value = name,
                                onValueChange = { name = it },
                                label = { Text("Name") },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Text
                                ),
                                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                            )
                            TextField(
                                value = amount,
                                onValueChange = { amount = it },
                                label = { Text("Amount") },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Decimal
                                ),
                                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                // Handle confirm action, e.g., save the data
                                val updatedEarn = earnState?.copy(name = name, amount = amount.toDoubleOrNull() ?: 0.0)
                                if (updatedEarn != null) {
                                    viewModel.onEvent(EarnEvent.insertEarn(updatedEarn))
                                }
                                showDialog = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog = false }
                        ) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        }
    }
}
