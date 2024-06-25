package com.wagdev.tassyir.core.presentation.common_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ImportantDevices
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.MoneyOffCsred
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ShowDrawer(
    navController: NavController
) {



    ModalDrawerSheet(modifier = Modifier
        .padding(7.dp)
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
                     imageVector = Icons.Default.Egg,
                    contentDescription = null
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

        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            modifier = Modifier.background(Color.White),
            icon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            label = { Text(text = "Profile") },
            selected = true,
            onClick = { /*TODO*/ }
        )
        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.MoneyOffCsred, contentDescription = null)
            },
            label = { Text(text = "expenses") },
            selected = true,
            onClick = { /*TODO*/ }
        )
        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.LocalActivity, contentDescription = null)
            },
            label = { Text(text = "Activities") },
            selected = true,
            onClick = { /*TODO*/ }
        )
        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.Notifications, contentDescription = null)
            },
            label = { Text(text = "Notifications") },
            selected = true,
            onClick = { /*TODO*/ }
        )
        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.Favorite, contentDescription = null)
            },
            label = { Text(text = "Favorite") },
            selected = true,
            onClick = { /*TODO*/ }
        )

        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.Schedule, contentDescription = null)
            },
            label = { Text(text = "Schedule") },
            selected = true,
            onClick = { /*TODO*/ }
        )
        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.Settings, contentDescription = null)
            },
            label = { Text(text = "Settengs") },
            selected = true,
            onClick = { /*TODO*/ }
        )
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.BarChart, contentDescription = null)
            },
            label = { Text(text ="Statistiques") },
            selected = true,
            onClick = { navController.navigate("login") }
        )
        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.ImportantDevices, contentDescription = null)
            },
            label = { Text(text = "Urgent Activities") },
            selected = true,
            onClick = { navController.navigate("contact") }
        )
        Spacer(Modifier.height(10.dp))
        NavigationDrawerItem(
            icon = {
                Icon(Icons.Default.Star, contentDescription = null)
            },
            label = { Text("les objectifs") },
            selected = true,
            onClick = { /*TODO*/ }
        )
    }
    // }
    // ){}
}


