package com.example.filmsdataapp.presentation.components.mainscreen.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.filmsdataapp.R

@Composable
fun ImageItem(width: Dp) {
    Image(
        painter = painterResource(id = R.drawable.test_image),
        contentDescription = null,
        modifier = Modifier
            .width(width)
            .height(200.dp)
            .background(Color.Gray),
        contentScale = ContentScale.Crop
    )
}