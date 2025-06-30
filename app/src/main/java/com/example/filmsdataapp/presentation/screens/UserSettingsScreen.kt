package com.example.filmsdataapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.filmsdataapp.presentation.components.Header
import com.example.filmsdataapp.presentation.components.usersettingsscreen.Content

@Composable
fun UserSettingsScreen(){
    Column(){
        Header()
        Content()
    }
}
