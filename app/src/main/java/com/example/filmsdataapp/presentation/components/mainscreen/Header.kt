package com.example.filmsdataapp.presentation.components.mainscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmsdataapp.R

@Composable
fun Header() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(33, 33, 33))
        .height(55.dp)

    ){
        Row(modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp, 0.dp)) {
            Image(
                painter = painterResource(id = R.drawable.menu_icon),
                contentDescription = "",
                Modifier
                    .size(35.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "FilmsBase",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.boldonse_regular))
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "",
                Modifier
                    .size(35.dp)
                    .align(Alignment.CenterVertically)
                    .scale(1.7f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "",
                Modifier
                    .size(35.dp)
                    .align(Alignment.CenterVertically)
                    .scale(1.5f)
            )
        }
    }
}
