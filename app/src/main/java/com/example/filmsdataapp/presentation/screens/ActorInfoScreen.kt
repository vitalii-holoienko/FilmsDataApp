package com.example.filmsdataapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.components.Header
import com.example.filmsdataapp.presentation.components.actorinfoscreen.Content
import com.example.filmsdataapp.ui.theme.BackGroundColor

@Composable
fun ActorInfoScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = BackGroundColor)){
        Header()
        Content()
    }
}