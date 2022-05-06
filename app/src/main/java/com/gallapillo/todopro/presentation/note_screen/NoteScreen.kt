package com.gallapillo.todopro.presentation.note_screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gallapillo.todopro.common.Screens
import com.gallapillo.todopro.domain.model.Todo
import com.gallapillo.todopro.presentation.TodoViewModel
import com.gallapillo.todopro.presentation.theme.*
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun NoteScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel = hiltViewModel(),
    id: String
) {
    val todos = viewModel.state.value.todos
    val todo = todos.firstOrNull { it.id == id.toInt() } ?: Todo(title = "Hello", subtitle = "World")
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }

    val context = LocalContext.current

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface(
                color = Background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = "Edit note",
                        fontFamily = GoogleSansBold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = {
                            Text(text = "title")
                        },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = { subtitle = it },
                        label = {
                            Text(text = "subtitle")
                        },
                        isError = title.isEmpty()
                    )
                    androidx.compose.material3.Button(
                        onClick = {
                            viewModel.updateTodo(
                                todo = Todo(title = title, subtitle = subtitle, color = todo.color, id = todo.id)
                            ) {
                                navHostController.navigate(Screens.Main.route)
                            }
                        }
                    ) {
                        Text(text = "Update")
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    backgroundColor = ColorsDecks[todo.color],
                    elevation = 8.dp
                ) {
                    Column (
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = todo.title,
                            fontSize = 24.sp,
                            fontFamily = GoogleSansBold,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                        Text(
                            text = todo.subtitle,
                            fontSize = 18.sp,
                            fontFamily = GoogleSansRegular,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    androidx.compose.material3.Button(
                        onClick = {
                            coroutineScope.launch {
                                title = todo.title
                                subtitle = todo.subtitle
                                bottomSheetState.show()
                            }
                        }
                    ) {
                        Text(text = "Update")
                    }
                    androidx.compose.material3.Button(
                        onClick = {
                            viewModel.deleteTodo(todo) {
                                navHostController.navigate(Screens.Main.route)
                            }
                        }
                    ) {
                        Text(text = "Remove")
                    }
                }
            }
        }
    }
}