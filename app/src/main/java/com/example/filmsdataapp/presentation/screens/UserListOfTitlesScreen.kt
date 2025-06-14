package com.example.filmsdataapp.presentation.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmsdataapp.presentation.components.Header
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.presentation.components.userlistoftitlesscreen.Content

@Composable
fun UserListOfTitlesScreen(list:String){
    Column{
        Header()
        Content(list)
    }
}