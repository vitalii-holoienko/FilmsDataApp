package com.example.filmsdataapp.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun Header(navigateToMainScreen : () -> Unit, navigateToProfileScreen : () -> Unit, onMenuClick : () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = PrimaryColor)
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
                    .clickable { onMenuClick() }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "FilmsBase",
                color = TextColor,
                modifier = Modifier.align(Alignment.CenterVertically).clickable {
                    navigateToMainScreen()
                },
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
                    .clickable { navigateToProfileScreen() }
            )
        }
    }
}
