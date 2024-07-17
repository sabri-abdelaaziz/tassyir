package com.wagdev.tassyir

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wagdev.noteapp.ui.theme.NoteappTheme
import com.wagdev.tassyir.core.controller.Navigations
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalLayoutApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            NoteappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigations(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



