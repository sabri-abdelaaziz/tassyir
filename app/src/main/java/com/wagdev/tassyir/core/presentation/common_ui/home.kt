package com.wagdev.tassyir.core.presentation.common_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddToHomeScreen
import androidx.compose.material.icons.filled.HowToReg
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.OfflinePin
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.DrawerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wagdev.tassyir.core.util.Screen
import com.wagdev.tassyir.R
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.wagdev.tassyir.earning_feature.presentation.Earns.EarnScreen
import com.wagdev.tassyir.goal_feature.presentation.goals.GoalScreen
import com.wagdev.tassyir.note_feature.presentation.notes.NoteScreen
import com.wagdev.tassyir.reminder_feature.presentation.ReminderScreen
import com.wagdev.tassyir.spending_feature.presentation.spends.SpendScreen
import com.wagdev.tassyir.task_feature.presentation.tasks.TaskScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    bottomItems: List<BottomItem>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var bottomNavState by rememberSaveable { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                DrawerHeader()

                // Items
                DrawerItems()
            }
        },
        content = {
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        bottomItems.forEachIndexed { i, e ->
                            NavigationBarItem(
                                modifier = Modifier.background(Color.Unspecified),
                                selected = i == bottomNavState,
                                onClick = {
                                    bottomNavState = i
                                    bottomItems.forEachIndexed { index, item ->
                                        item.isSelected = index == i
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (i == bottomNavState) e.selectedIcon else e.unselectedIcon,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    if( i == bottomNavState){
                                        Text(e.title)
                                    }
                                }
                            )
                        }
                    }
                },
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                modifier = Modifier.clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            )
                        },
                        actions = {
                            Row {
                                Icon(Icons.Default.OfflinePin, contentDescription = null)
                                Icon(Icons.Default.AddToHomeScreen, contentDescription = null)
                                Icon(Icons.Default.HowToReg, contentDescription = null)
                                Icon(Icons.Default.Search, contentDescription = null)
                            }
                        },
                        title = { Text(stringResource(id = R.string.app_name)) }
                    )
                },
                modifier = modifier,
                //floatingActionButton = {
                  //  FloatingActionButton(
                    //    onClick = {
                      //      navController.navigate(Screen.NotesScreen.route)
                       //}
                    //) {
                      //  Icon(Icons.Default.Add, contentDescription = "Notes")
                    //}
                //},
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (bottomNavState) {
                          //  0 -> HomeContent()
                            0 -> NoteScreen(navController = navController)
                            1 -> TaskScreen(navController=navController)

                            2 -> GoalScreen(navController=navController)
                            3 -> EarnScreen(navController=navController)
                            4 -> SpendScreen(navController=navController)
                            5 -> ReminderScreen(navController=navController)
                            // Ajoutez plus de cas en fonction du nombre d'éléments dans votre barre de navigation
                            else -> Text("Invalid selection")
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun HomeContent() {
    Text("Home Content", style = TextStyle(color = Color.Black))
}

@Composable
fun SecondContent() {
    Text("Second Content", style = TextStyle(color = Color.Black))
}

@Composable
fun ThirdContent() {
    Text("Third Content", style = TextStyle(color = Color.Black))
}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Navigation Drawer",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.White, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun DrawerItems() {
    Column {
        val drawerItems = listOf("Home", "Profile", "Settings", "Help")
        drawerItems.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item) },
                selected = false, // Set to true if the item is selected
                onClick = {
                    // Handle navigation here
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}


