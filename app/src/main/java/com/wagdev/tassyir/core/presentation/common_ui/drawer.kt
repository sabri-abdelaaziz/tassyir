package com.wagdev.tassyir.core.presentation.common_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GolfCourse
import androidx.compose.material.icons.filled.ImportantDevices
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.MoneyOffCsred
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.rounded.LocalActivity
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.MoneyOffCsred
import androidx.compose.material.icons.rounded.Note
import androidx.compose.material.icons.rounded.Timelapse
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wagdev.tassyir.core.util.Screen

@Composable
fun ShowDrawer(
    navController: NavController
) {

  val listItems= listOf(
      DrawerItem(
          icon = Icons.Default.Schedule,
          label = "Schedule",
          root =Screen.Schedule.route
      ),
      DrawerItem(
          icon = Icons.Default.Task,
          label = "Tasks",
          root = Screen.TaskScreen.route
      ),
              DrawerItem(
              icon = Icons.Default.GolfCourse,
      label = "Goals",
      root = Screen.Goals.route,
  ),
    DrawerItem(
        icon = Icons.Rounded.Money,
        label = "Earning",
        root =Screen.Earning.route ,
    ),
      DrawerItem(
          icon = Icons.Rounded.Timelapse,
          label = "Reminder",
          root =Screen.Reminder.route ,
      ),
      DrawerItem(
          icon = Icons.Rounded.Note,
          label = "Notes",
          root =Screen.NotesScreen.route ,
      ),
      DrawerItem(
          icon = Icons.Rounded.LocalActivity,
          label = "Activities",
          root =Screen.Activities.route ,
      ),
      DrawerItem(
          icon = Icons.Rounded.MoneyOffCsred,
          label = "Spending",
          root =Screen.Spending.route,
      )

  )

    ModalDrawerSheet(modifier = Modifier
        .background(Color.DarkGray)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Icon(
                     imageVector = Icons.Default.Person,
                    contentDescription = null,
                    Modifier.size(33.dp)
                )
                Text(
                    "Sabri Abdelaaziz",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text("azizsabri072@gmail.com", style = TextStyle(fontSize = 11.sp))
            }

        }

        Spacer(Modifier.height(16.dp))
        listItems.forEach { item ->
            NavigationDrawerItem(
                colors = NavigationDrawerItemDefaults.colors(Color(0xFF009688)),
                icon = {
                    Icon(item.icon, contentDescription = null)
                },
                label = { Text(text = item.label) },
                selected = true,
                onClick = {
                    navController.navigate(item.root) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
    // }
    // ){}
}

data class DrawerItem(
    val icon: ImageVector,
    val label:String,
    val root:String
)


