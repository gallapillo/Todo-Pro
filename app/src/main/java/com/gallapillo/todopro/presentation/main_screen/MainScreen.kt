package com.gallapillo.todopro.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.gallapillo.todopro.presentation.theme.TodoBackground

@Composable
fun MainScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        floatingActionButton = {
            androidx.compose.material3.FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screens.Add.route)
                },
                containerColor = TodoBackground,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon", tint = Color.White)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(20.dp)
        ) {
            items(state.todos) { todo ->
                TodoCard(
                    todo = todo,
                    navHostController = navHostController
                )
            }
        }
    }
}

@Composable
fun TodoCard(
    todo: Todo,
    navHostController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 24.dp)
            .clickable {
                navHostController.navigate(Screens.Note.route)
            },
        elevation = 6.dp,
        backgroundColor = TodoBackground
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = todo.title,
                fontSize = 24.sp,
                fontFamily = GoogleSansBold,
                color = Background
            )
            Text(
                text = todo.subtitle,
                fontFamily = GoogleSansRegular,
                color = Background
            )
        }
    }
}