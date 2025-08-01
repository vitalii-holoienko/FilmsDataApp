package com.example.filmsdataapp.presentation.components.newsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.TextColor
@Composable
fun Content(news: News, viewModel : MainActivityViewModel){
    Column(modifier = Modifier.background(BackGroundColor).padding(10.dp).fillMaxWidth().verticalScroll(state = ScrollState(0))){
        Text(
            text = news.articleTitle!!.plainText!!,
            color = TextColor,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            fontFamily = FontFamily(Font(R.font.roboto_black)),
            modifier = Modifier.padding(0.dp),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = news.text!!.plainText!!,
            color = TextColor,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
            modifier = Modifier.padding(0.dp),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = rememberAsyncImagePainter(news.image!!.url),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(400.dp),
            contentScale = ContentScale.Fit
        )
    }

}