package com.example.filmsdataapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.filmsdataapp.presentation.components.Header
import com.example.filmsdataapp.presentation.components.usersettingsscreen.Content
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel

@Composable
fun UserSettingsScreen(viewModel : MainActivityViewModel){
    Column(){
        Header(viewModel)
        Content(viewModel)
    }
}
