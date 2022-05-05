package com.gallapillo.todopro.presentation.note_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gallapillo.todopro.presentation.TodoViewModel
import com.gallapillo.todopro.presentation.theme.Background
import com.gallapillo.todopro.presentation.theme.GoogleSansBold
import com.gallapillo.todopro.presentation.theme.GoogleSansRegular
import com.gallapillo.todopro.presentation.theme.TodoBackground

@Composable
fun NoteScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel = hiltViewModel()
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
                    .fillMaxSize()
                    .padding(32.dp),
                backgroundColor = Background
            ) {
                Column (
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "title",
                        fontSize = 24.sp,
                        fontFamily = GoogleSansBold,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    Text(
                        text = "Subtitle",
                        fontSize = 18.sp,
                        fontFamily = GoogleSansRegular,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}