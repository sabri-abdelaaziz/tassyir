package com.wagdev.tassyir.core.presentation.common_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.HowToReg
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.OfflinePin
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wagdev.tassyir.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.wagdev.tassyir.core.util.BottomItem
import com.wagdev.tassyir.core.util.GridContent
import com.wagdev.tassyir.earning_feature.presentation.Earns.EarnScreen
import com.wagdev.tassyir.goal_feature.presentation.goals.GoalScreen
import com.wagdev.tassyir.note_feature.presentation.notes.NoteScreen
import com.wagdev.tassyir.reminder_feature.presentation.ReminderScreen
import com.wagdev.tassyir.spending_feature.presentation.spends.SpendScreen
import com.wagdev.tassyir.task_feature.presentation.tasks.TaskScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    bottomItems: List<BottomItem>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Drawer state and scope
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Bottom navigation state

    var bottomNavState by rememberSaveable { mutableIntStateOf(0) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ShowDrawer(navController=navController)
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

                                    BadgedBox(
                                        badge = {
                                            if (e.badgeNum > 0) {
                                                Badge { Text(e.badgeNum.toString()) }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (i == bottomNavState) e.selectedIcon else e.unselectedIcon,
                                            contentDescription = null
                                        )
                                    }
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
                        colors =  TopAppBarDefaults.mediumTopAppBarColors(),
                        modifier = Modifier.padding(5.dp),
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                modifier = Modifier
                                    .size(26.dp)
                                    .clickable {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                            )
                        },
                        actions = {
                            Row {
                                Icon(Icons.Default.MoreVert, contentDescription = null,Modifier.size(26.dp))
                                  }
                        },
                        title = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(stringResource(id = R.string.app_name),style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, fontFamily = FontFamily.SansSerif,fontSize = 26.sp))
                            }
                        }
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),

                    ) {
                        when (bottomNavState) {
                            0 -> HomeContent(modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxSize())
                            1 -> NoteScreen(navController = navController)
                            2 -> TaskScreen(navController=navController)

                            3 -> GoalScreen(navController=navController)
                            4 -> EarnScreen(navController=navController)
                            5 -> SpendScreen(navController=navController)
                            6 -> ReminderScreen(navController=navController)
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
fun HomeContent(modifier: Modifier = Modifier) {
    val images = listOf(R.drawable.manage1, R.drawable.manage2, R.drawable.manage3) // Add your image resources here
    val gridItems= listOf(
        GridContent("Notes","manage notes",R.drawable.notes,),
        GridContent("t2","d2",R.drawable.manage2,),
        GridContent("t3","d3",R.drawable.manage3,),
        GridContent("t4","d4",R.drawable.manage1,),
        GridContent("t5","d5",R.drawable.manage2,),
        GridContent("t5","d5",R.drawable.manage2,)
    )
    // State to hold the current image index
    var currentIndex by remember { mutableIntStateOf(0) }

    // Launch an effect that changes the image every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000L) // Delay for 3 seconds
            currentIndex = (currentIndex + 1) % images.size
        }
    }


    Column(modifier=modifier) {
        //Search Section
        SearchSection()
        //Features elements
        Text("Features", style = TextStyle(color = Color.Black, fontSize = 25.sp),modifier = Modifier.padding(10.dp))
        //LazyVerticalGrid(columns = GridCells.Fixed(3)) {
           // items(gridItems){
            //    GridItem(content = it)
            //}
        //}
        LazyRow {
            items(gridItems){
                RowItem(content = it)
                }
        }
        Text("Statistiques", style = TextStyle(color = Color.Black, fontSize = 25.sp),modifier = Modifier.padding(10.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
         items(gridItems) {
             GridItem(content = it)
         }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = images[currentIndex]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text("Home Content", style = TextStyle(color = Color.Black))
    }

}






@Composable
fun RowItem(
    modifier: Modifier = Modifier,
    content: GridContent
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(16.dp)),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = content.image),
                contentDescription = content.title,
                modifier = Modifier
                    .fillMaxWidth()

                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = content.title,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = content.description,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Composable
fun GridItem(
    modifier: Modifier = Modifier,
    content: GridContent
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),

    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(16.dp),
           verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = content.title,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Text(
                text = content.description,
                style = TextStyle(
                    color = Color.Green,
                    fontSize = 14.sp
                )
            )
        }
    }
}


@Preview
@Composable
fun SearchSection(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Today Tasks", color = Color.Black, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "12 Tasks", color = Color.Gray)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Details, contentDescription = null, tint = Color.Gray)
                }
            }
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray, modifier = Modifier.size(34.dp))
        }
    }
}

