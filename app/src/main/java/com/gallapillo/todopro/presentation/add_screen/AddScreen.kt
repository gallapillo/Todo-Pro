package com.gallapillo.todopro.presentation.add_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gallapillo.todopro.common.Screens
import com.gallapillo.todopro.domain.model.Todo
import com.gallapillo.todopro.presentation.TodoViewModel
import com.gallapillo.todopro.presentation.theme.Background
import com.gallapillo.todopro.presentation.theme.GoogleSansBold
import com.gallapillo.todopro.presentation.theme.GoogleSansRegular

@Composable
fun AddScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Add new note",
                fontSize = 18.sp,
                fontFamily = GoogleSansBold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Note title", fontFamily = GoogleSansRegular) }
            )

            OutlinedTextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text(text = "Note subtitle", fontFamily = GoogleSansRegular, color = Background) }
            )
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    val todo = Todo(title, subtitle)
                    viewModel.addTodo(todo)
                    navHostController.navigate(Screens.Main.route)
                }
            ) {
                Text(text = "Add note", fontFamily = GoogleSansRegular)
            }
        }
    }
}