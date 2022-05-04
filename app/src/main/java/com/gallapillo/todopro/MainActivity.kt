package com.gallapillo.todopro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gallapillo.todopro.common.Screens
import com.gallapillo.todopro.presentation.add_screen.AddScreen
import com.gallapillo.todopro.presentation.main_screen.MainScreen
import com.gallapillo.todopro.presentation.note_screen.NoteScreen
import com.gallapillo.todopro.presentation.start_screen.StartScreen
import com.gallapillo.todopro.presentation.theme.TodoProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoProTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold (
                        topBar = {
                             TopAppBar(
                                 title = {
                                     Text(text = "Notes")
                                 },
                                 backgroundColor = Color.Blue,
                                 contentColor = Color.White,
                                 elevation = 12.dp
                             )
                        },
                        content = {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                NavHost(navController = navController, startDestination = Screens.Start.route) {
                                    composable(Screens.Start.route) {
                                        StartScreen(navHostController = navController)
                                    }
                                    composable(Screens.Main.route) {
                                        MainScreen(navHostController = navController)
                                    }
                                    composable(Screens.Add.route) {
                                        AddScreen(navHostController = navController)
                                    }
                                    composable(Screens.Note.route) {
                                        NoteScreen(navHostController = navController)
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
