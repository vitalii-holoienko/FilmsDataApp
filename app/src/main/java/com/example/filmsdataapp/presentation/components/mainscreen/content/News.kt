package com.example.filmsdataapp.presentation.components.mainscreen.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.ui.theme.PrimaryColor

@Composable
fun News(news : List<News>, navigateToNewsScreen : (News) -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        if(!news.isEmpty()){
            news.forEach{
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .background(PrimaryColor),
                    contentAlignment = Alignment.Center
                    )
                {
                    Column(modifier = Modifier.padding(7.dp).clickable { navigateToNewsScreen(it) }) {
                        Image(
                            painter = rememberAsyncImagePainter(it.image!!.url),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color.Gray),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = it.articleTitle!!.plainText!!,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis

                        )
                    }

                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        }


    }
}