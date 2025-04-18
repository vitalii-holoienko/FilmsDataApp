package com.example.filmsdataapp.presentation.components.listofmoviesscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmsdataapp.R
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.TextColor


@Composable
fun Content(from : String){
    var pageName = ""
    var pageDescription = ""
    if(from == "Currently Trending"){
        pageName = "Currently Trending"
        pageDescription = "This page displays currently popular movies,\nsorted by rating"
    }
    if(from == "Coming soon"){
        pageName = "Coming soon"
        pageDescription = "This page displays coming soon movies"
    }
    if(from == "Movies"){
        pageName = "Movies"
        pageDescription = "This page displays list of movies,\nsorted by rating"
    }
    if(from == "Actors"){
        pageName = "Actors"
        pageDescription = "This page displays list of actors,\nsorted in alphabetical order"
    }
    if(from == "TVShows"){
        pageName = "TVShows"
        pageDescription = "This page displays list of TV shows,\nsorted by rating"
    }


    val images = listOf(
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
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val totalHorizontalPadding = 10.dp * 3
    val imageWidth = (screenWidth - totalHorizontalPadding) / 2
    val rows = images.chunked(2)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ){

        var isFilterVisible by remember { mutableStateOf(false) }
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val filterWidth = 300.dp
        val density = LocalDensity.current
        val offsetX by animateFloatAsState(
            targetValue = if (isFilterVisible) 0f else with(density) { filterWidth.toPx() },
            animationSpec = tween(durationMillis = 300),
            label = "offsetX"
        )
        val filterWidthPx = with(density) { filterWidth.toPx() }
        val buttonOffsetX by animateFloatAsState(
            targetValue = if (isFilterVisible) 0f else filterWidthPx,
            animationSpec = tween(300),
            label = "buttonOffset"
        )








        Column(){
            Spacer(modifier= Modifier.height(20.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(10.dp, 0.dp)){
                Text(
                    text = pageName,
                    color = TextColor,
                    fontSize = 27.sp,
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = pageDescription,
                    color = TextColor,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                )
            }



            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)

            ) {
                rows.forEach { rowImages ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowImages.forEach { imageRes ->
                            Column(){
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(imageWidth)
                                        .height(250.dp)
                                )
                                Text(
                                    text = "Lorem ipsum Lorem ipsum",
                                    color = TextColor,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .width(imageWidth)
                                        .padding(5.dp, 3.dp)
                                )
                                Row(modifier = Modifier.width(imageWidth)){
                                    Text(
                                        text = "Movie",
                                        color = TextColor,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(5.dp,3.dp)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = "2015",
                                        color = TextColor,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(5.dp,3.dp)
                                    )
                                }


                            }


                        }
                        if (rowImages.size == 1) {
                            Spacer(modifier = Modifier.width(imageWidth))
                        }
                    }
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        }
        FilterPanelWithButton(
            isFilterVisible = isFilterVisible,
            onToggle = { isFilterVisible = !isFilterVisible },
            screenHeight
        )
        }




}