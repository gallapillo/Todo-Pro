package com.gallapillo.todopro.presentation.start_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gallapillo.todopro.common.Constants.TYPE_DATABASE
import com.gallapillo.todopro.common.Constants.TYPE_FIREBASE
import com.gallapillo.todopro.common.Screens
import com.gallapillo.todopro.presentation.TodoViewModel
import com.gallapillo.todopro.presentation.theme.Background
import com.gallapillo.todopro.presentation.theme.GoogleSansRegular

@Composable
fun StartScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel
) {
    val context = LocalContext.current

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Background
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "What will you use?",
                fontFamily = GoogleSansRegular
            )
            androidx.compose.material3.Button(
                onClick = {
                    viewModel.getTodoFromDatabase(TYPE_DATABASE) {
                        navHostController.navigate(Screens.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Local database",
                    fontFamily = GoogleSansRegular
                )
            }
            androidx.compose.material3.Button(
                onClick = {
                    viewModel.getTodoFromDatabase(TYPE_FIREBASE) {
                        navHostController.navigate(Screens.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Online database",
                    fontFamily = GoogleSansRegular
                )
            }
        }
    }
}