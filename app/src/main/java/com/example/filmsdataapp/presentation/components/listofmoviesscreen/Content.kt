package com.example.filmsdataapp.presentation.components.listofmoviesscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmsdataapp.R
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor


@Composable
fun Content(from : String){
    var pageName = ""
    var pageDescription = ""
    if(from == "Currently Trending"){
        pageName = "Currently Trending"
        pageDescription = "This page displays currently popular movies,\nsorted by rating"
    }
    if(from == "New Releases"){
        pageName = "New Releases"
        pageDescription = "This page displays recently released movies,\nsorted by rating"
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
    val totalHorizontalPadding = 10.dp * 3
    val imageWidth = (screenWidth - totalHorizontalPadding) / 2
    val rows = images.chunked(2)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
    ){



        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .offset(x = 25.dp, y = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = PrimaryColor)
                .width(60.dp)
                .height(85.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    //TODO
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationZ = -90f
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Image(
                    painter = painterResource(id = R.drawable.filter_arrow_2_left),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .graphicsLayer {
                            rotationZ = -270f
                        }
                        .padding(0.dp, 0.dp, 40.dp, 0.dp)
                        .wrapContentWidth(unbounded = true)
                )
            }
        }



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
        }




}