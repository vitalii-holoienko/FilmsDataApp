package com.example.filmsdataapp.presentation.components.userhistoryscreen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun Content(){
    val history = remember { mutableStateOf<List<String>>(emptyList()) }
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)
    val error = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchUserHistory(
            onResult = { messages ->
                history.value = messages
            },
            onError = { exception ->
                error.value = exception.message
            }
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {


        if (error.value != null) {
            Text("Error: ${error.value}", color = Color.Red)
        }

        if (history.value.isEmpty()) {
            Text("No history", color = TextColor)
        } else {
            Text("History", color = TextColor, fontSize = 35.sp,modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.height(10.dp))

            Spacer(
                modifier = Modifier.fillMaxWidth()
                    .height(1.dp).drawBehind {
                        drawLine(
                            color = Color.Gray,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 1.dp.toPx()
                        )
                    }
            )
            Spacer(modifier = Modifier.height(5.dp))
            LazyColumn {
                items(history.value) { message ->
                    Text(text = message, modifier = Modifier.padding(8.dp), color = TextColor)
                    Spacer(modifier = Modifier.height(5.dp))
                    Spacer(
                        modifier = Modifier.fillMaxWidth()
                            .height(1.dp).drawBehind {
                                drawLine(
                                    color = Color.Gray,
                                    start = Offset(0f, size.height),
                                    end = Offset(size.width, size.height),
                                    strokeWidth = 1.dp.toPx()
                                )
                            }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }

}