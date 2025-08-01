package com.example.filmsdataapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.filmsdataapp.presentation.components.Header
import com.example.filmsdataapp.presentation.components.userhistoryscreen.Content
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel


@Composable
fun UserHistoryScreen(viewModel : MainActivityViewModel){
    Column{
        Header(viewModel)
        Content(viewModel)
    }

}