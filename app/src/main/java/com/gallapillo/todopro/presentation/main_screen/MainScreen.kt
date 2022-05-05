package com.gallapillo.todopro.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gallapillo.todopro.common.Screens
import com.gallapillo.todopro.presentation.theme.GoogleSansBold
import com.gallapillo.todopro.presentation.theme.GoogleSansRegular

@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    Scaffold(
        floatingActionButton = {
            androidx.compose.material3.FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screens.Add.route)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon", tint = Color.White)
            }
        }
    ) {
        Column(

        ) {
            NoteCard(
                title = "Note 1",
                subtitle = "Subtitle note",
                navHostController
            )
            NoteCard(
                title = "Note 2",
                subtitle = "Subtitle note",
                navHostController
            )
            NoteCard(
                title = "Note 3",
                subtitle = "Subtitle note",
                navHostController
            )
            NoteCard(
                title = "Note 4",
                subtitle = "Subtitle note",
                navHostController
            )
        }
    }
}

@Composable
fun NoteCard(
    title: String,
    subtitle: String,
    navHostController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navHostController.navigate(Screens.Note.route)
            },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontFamily = GoogleSansBold
            )
            Text(
                text = subtitle,
                fontFamily = GoogleSansRegular
            )
        }
    }
}