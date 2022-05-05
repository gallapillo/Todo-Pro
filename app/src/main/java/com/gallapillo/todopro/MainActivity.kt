package com.gallapillo.todopro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gallapillo.todopro.common.Screens
import com.gallapillo.todopro.presentation.TodoViewModel
import com.gallapillo.todopro.presentation.add_screen.AddScreen
import com.gallapillo.todopro.presentation.main_screen.MainScreen
import com.gallapillo.todopro.presentation.note_screen.NoteScreen
import com.gallapillo.todopro.presentation.start_screen.StartScreen
import com.gallapillo.todopro.presentation.theme.GoogleSansRegular
import com.gallapillo.todopro.presentation.theme.Primary
import com.gallapillo.todopro.presentation.theme.TodoProTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoProTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val viewModel = hiltViewModel<TodoViewModel>()
                    Scaffold (
                        topBar = {
                             TopAppBar(
                                 title = {
                                     Text(
                                         text = "Todo Pro",
                                         fontSize = 32.sp,
                                         modifier = Modifier.padding(top = 12.dp),
                                         fontFamily = GoogleSansRegular
                                     )
                                 },
                                 backgroundColor = Primary,
                                 contentColor = Color.Black,
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
                                        StartScreen(navHostController = navController, viewModel = viewModel)
                                    }
                                    composable(Screens.Main.route) {
                                        MainScreen(navHostController = navController, viewModel = viewModel)
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
