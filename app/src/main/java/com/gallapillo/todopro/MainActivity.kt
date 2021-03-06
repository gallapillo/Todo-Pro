package com.gallapillo.todopro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gallapillo.todopro.common.Constants.TYPE_FIREBASE
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
@ExperimentalMaterialApi
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
                                     Row (
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(horizontal = 16.dp),
                                         horizontalArrangement = Arrangement.SpaceBetween
                                     ) {
                                         Text(
                                             text = "Todo Pro",
                                             fontSize = 32.sp,
                                             modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                             fontFamily = GoogleSansRegular
                                         )
                                         if (navController.currentBackStackEntry?.arguments?.getString("DbType", "") == TYPE_FIREBASE) {
                                             androidx.compose.material3.Icon(
                                                 imageVector = Icons.Default.ExitToApp,
                                                 contentDescription = "",
                                                 modifier = Modifier.clickable {
                                                     if (navController.currentBackStackEntry?.arguments?.getString("DbType", "") == TYPE_FIREBASE) {
                                                         viewModel.signOut(TYPE_FIREBASE) {
                                                             navController.navigate(Screens.Start.route) {
                                                                 popUpTo(Screens.Start.route) {
                                                                     inclusive = true
                                                                 }
                                                             }
                                                         }
                                                     }
                                                 }
                                             )
                                         }
                                     }
                                 },
                                 backgroundColor = Primary,
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
                                        StartScreen(navHostController = navController, viewModel = viewModel)
                                    }
                                    composable(Screens.Main.route + "/{DbType}") { backStackEntry ->
                                        MainScreen(navHostController = navController, viewModel = viewModel, dbType = backStackEntry.arguments?.getString("DbType") ?: "")
                                    }
                                    composable(Screens.Add.route  + "/{DbType}") { backStackEntry ->
                                        AddScreen(navHostController = navController, viewModel = viewModel, dbType = backStackEntry.arguments?.getString("DbType") ?: "")
                                    }
                                    composable(Screens.Note.route + "/{Id}/{DbType}") { backStackEntry ->
                                        NoteScreen(navHostController = navController, viewModel = viewModel, id = backStackEntry.arguments?.getString("Id") ?: "1", dbType = backStackEntry.arguments?.getString("DbType") ?: "")
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
