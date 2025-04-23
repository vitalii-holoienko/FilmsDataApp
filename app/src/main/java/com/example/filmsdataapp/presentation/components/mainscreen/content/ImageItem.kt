package com.example.filmsdataapp.presentation.components.mainscreen.content

import Movie
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.ui.theme.LinksColor
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun ImageItem(width: Dp, movie: Movie) {
    Column{
        Image(
            painter = rememberAsyncImagePainter(movie.primaryImage),
            contentDescription = null,
            modifier = Modifier
                .width(width)
                .height(170.dp)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Text(
            text = movie.originalTitle ?: "",
            color = LinksColor,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(width)
                .padding(0.dp, 5.dp, 0.dp, 0.dp)
        )
        Row(modifier = Modifier.width(width)) {
            Text(
                text = movie.type!!.replaceFirstChar { it.uppercase() },
                color = TextColor,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(0.dp, 3.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (movie.startYear!! < 2025) "2025" else movie.startYear.toString(),
                color = TextColor,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(5.dp, 3.dp)
            )
        }
    }

}