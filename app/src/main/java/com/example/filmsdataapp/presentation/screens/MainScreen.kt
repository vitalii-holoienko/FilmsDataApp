package com.example.filmsdataapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.filmsdataapp.presentation.components.mainscreen.content.Content
import com.example.filmsdataapp.presentation.components.mainscreen.Header

@Composable
fun MainScreen(
    navigateToNewReleasesPage : () -> Unit,
    navigateToCurrentlyTrendingPage : () ->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color= Color(36,36,36)).verticalScroll(rememberScrollState())
    ){
        Header()
        Content(
            navigateToNewReleasesPage,
            navigateToCurrentlyTrendingPage
        )
    }

}
