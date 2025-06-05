package com.example.filmsdataapp.presentation.components.mainscreen.content

import Movie
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.LinksColor
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun ImageItem(width: Dp, movie: Title?) {
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)
    if(movie != null){
        val painter = rememberAsyncImagePainter(movie.primaryImage)
        val imageState = painter.state

        Column {
            Box(
                modifier = Modifier
                    .width(width)
                    .height(170.dp)
                    .clickable(enabled = movie.primaryImage != null) {
                        viewModel.onTitleClicked(movie)
                    }
            ) {
                if (movie.primaryImage != null) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .matchParentSize()
                            .placeholder(
                                visible = imageState is AsyncImagePainter.State.Loading,
                                highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                                color = Color.LightGray
                            ),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                                color = Color.LightGray
                            )
                    )
                }
            }

            Text(
                text = movie.originalTitle ?: "",
                color = LinksColor,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .width(width)
                    .padding(top = 5.dp)
            )

            Row(modifier = Modifier.width(width)) {
                Text(
                    text = movie.type?.replaceFirstChar { it.uppercase() } ?: "",
                    color = TextColor,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 3.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = if ((movie.startYear ?: 2025) < 2025) "2025" else movie.startYear.toString(),
                    color = TextColor,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 5.dp, top = 3.dp)
                )
            }
        }
    }else{
        Column {
            Box(
                modifier = Modifier
                    .width(width)
                    .height(170.dp)
                    .placeholder(
                        visible = true,
                        color = PrimaryColor,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.Gray)
                    )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .width(width)
                    .height(16.dp)
                    .placeholder(
                        visible = true,
                        color = PrimaryColor,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.Gray)
                    )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(modifier = Modifier.width(width)) {
                Box(
                    modifier = Modifier
                        .width(width * 0.4f)
                        .height(12.dp)
                        .placeholder(
                            visible = true,
                            color = PrimaryColor,
                            highlight = PlaceholderHighlight.shimmer(highlightColor = Color.Gray)
                        )
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .width(width * 0.2f)
                        .height(12.dp)
                        .placeholder(
                            visible = true,
                            color = PrimaryColor,
                            highlight = PlaceholderHighlight.shimmer(highlightColor = Color.Gray)
                        )
                )
            }
        }
    }

}