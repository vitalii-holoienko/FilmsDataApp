package com.example.filmsdataapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.filmsdataapp.presentation.components.Header
import com.example.filmsdataapp.presentation.components.listofmoviesscreen.Content
import com.example.filmsdataapp.ui.theme.BackGroundColor

@Composable
fun ComingSoonScreen(navigateToMainScreen : () -> Unit, navigateToProfilePage : () ->Unit, onMenuClick : () -> Unit){
    Column(modifier = Modifier.fillMaxSize()
        .background(color= BackGroundColor).verticalScroll(rememberScrollState())){
        Header(navigateToMainScreen, navigateToProfilePage, onMenuClick)
        Content("Coming soon")
    }

}