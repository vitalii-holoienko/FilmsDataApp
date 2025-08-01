package com.example.filmsdataapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.components.Header
import com.example.filmsdataapp.presentation.components.newsscreen.Content
import com.example.filmsdataapp.presentation.components.profilescreen.Content
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor

@Composable
fun NewsScreen(news: News, viewModel : MainActivityViewModel){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = BackGroundColor)){
        Header(viewModel)
        Content(news, viewModel)
    }
}