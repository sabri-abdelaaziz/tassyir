package com.wagdev.tassyir

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wagdev.noteapp.ui.theme.NoteappTheme
import com.wagdev.tassyir.core.controller.Navigations
import com.wagdev.tassyir.core.presentation.common_ui.Activities
import com.wagdev.tassyir.core.presentation.common_ui.Expenses
import com.wagdev.tassyir.core.presentation.common_ui.Home
import com.wagdev.tassyir.core.presentation.common_ui.Schedule
import com.wagdev.tassyir.core.presentation.common_ui.Splash
import com.wagdev.tassyir.note_feature.presentation.add_edit_note.AddEditNoteScreen
import com.wagdev.tassyir.note_feature.presentation.notes.NoteScreen
import com.wagdev.tassyir.core.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalLayoutApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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



