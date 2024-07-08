package com.wagdev.tassyir.spending_feature.presentation.spends

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition",
    "UnrememberedMutableState"
)
@Composable
fun SpendScreen(
    modifier: Modifier = Modifier,
    viewModel:SpendViewModel = hiltViewModel(),
    navController: NavController

    ) {
    val state = viewModel.state.value.spedList.collectAsState(initial = emptyList())
    var isLoading by remember{
        mutableStateOf(false)
    }
    val spend=viewModel.state.value.spend

    Scaffold(
        modifier=modifier,
        content= {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                LazyColumn {
                    itemsIndexed(state.value) { i, e ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = e.name)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = e.amount.toString())

                            Divider()
                        }
                    }
                }
            }

            if (isLoading) {
                AlertDialog(
                    onDismissRequest = { isLoading=false },
                    title = { Text("Enter Details of ") },
                    text = {
                        Column {
                            if (spend != null) {
                                TextField(
                                    value = spend.name,
                                    onValueChange = { spend.name = it },
                                    label = { Text("Name") },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Text
                                    ),
                                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                                )
                            }
                            if (spend != null) {
                                TextField(
                                    value = spend.amount.toString(),
                                    onValueChange = { spend.amount = it.toDouble() },
                                    label = { Text("Amount") },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Decimal
                                    ),
                                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                                )
                            }
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                               if (spend != null) {
                                    viewModel.onEvent(SpendEvent.AddEditSpend(spend))
                                }
                                isLoading = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { isLoading = false }
                        ) {
                            Text("Dismiss")
                        }
                    }
                )

            }
        }
        ,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isLoading=true
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    )

}