package com.gallapillo.todopro.presentation.start_screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gallapillo.todopro.common.Constants.TYPE_DATABASE
import com.gallapillo.todopro.common.Constants.TYPE_FIREBASE
import com.gallapillo.todopro.common.Screens
import com.gallapillo.todopro.domain.model.Todo
import com.gallapillo.todopro.presentation.TodoViewModel
import com.gallapillo.todopro.presentation.theme.Background
import com.gallapillo.todopro.presentation.theme.GoogleSansBold
import com.gallapillo.todopro.presentation.theme.GoogleSansRegular
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun StartScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel
) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                        text = "Sign in your account",
                        fontFamily = GoogleSansBold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(text = "Email")
                        },
                        isError = email.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(text = "Password")
                        },
                        isError = password.isEmpty()
                    )
                    androidx.compose.material3.Button(
                        onClick = {
                            viewModel.getTodoFromFirebase(email, password, {
                                navHostController.navigate(Screens.Main.route)
                            }) {
                                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                            }
                        },
                        enabled = email.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = "Sing in")
                    }
                }
            }
        }
    ) {
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
                        coroutineScope.launch {
                            bottomSheetState.show()
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
}