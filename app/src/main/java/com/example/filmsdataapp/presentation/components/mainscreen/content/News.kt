package com.example.filmsdataapp.presentation.components.mainscreen.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmsdataapp.R

@Composable
fun News(){
    Column(modifier = Modifier.fillMaxSize()){
        val news = listOf(
            R.drawable.test_image,
            R.drawable.test_image,
            R.drawable.test_image,
            R.drawable.test_image,
            R.drawable.test_image,
            R.drawable.test_image,
            R.drawable.test_image,
            R.drawable.test_image,
            R.drawable.test_image,
            )
        news.forEach{
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum  Lorem ipsum Lorem i",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )
            Spacer(modifier = Modifier.height(50.dp))
        }

    }
}