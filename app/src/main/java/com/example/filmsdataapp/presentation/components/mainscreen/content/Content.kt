package com.example.filmsdataapp.presentation.components.mainscreen.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
fun Content(
    navigateToNewReleasesPage : () -> Unit,
    navigateToCurrentlyTrendingPage : () ->Unit,
) {
    Spacer(modifier = Modifier.height(40.dp))
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ){
        Column(){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = PrimaryColor)
                .clickable {
                    navigateToNewReleasesPage()
                }
            ){
                Text(
                    text = "New Releases",
                    color = TextColor,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(5.dp, 0.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.triangle_go_to_icon),
                    contentDescription = "",
                    Modifier
                        .size(25.dp)
                        .align(Alignment.CenterEnd)
                        .scale(1f)
                        .padding(5.dp, 0.dp)

                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(color = PrimaryColor),
                contentAlignment = Alignment.Center
            ){
                ImageSlider()
            }

            Spacer(modifier = Modifier.height(70.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = PrimaryColor)
                .clickable { navigateToCurrentlyTrendingPage() }
            ){
                Text(
                    text = "Currently Trending",
                    color = TextColor,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(5.dp, 0.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.triangle_go_to_icon),
                    contentDescription = "",
                    Modifier
                        .size(25.dp)
                        .align(Alignment.CenterEnd)
                        .scale(1f)
                        .padding(5.dp, 0.dp)

                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(color = PrimaryColor),
                contentAlignment = Alignment.Center
            ){
                ImageSlider()
            }
            Spacer(modifier = Modifier.height(40.dp))
            //MAIN TAGS
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)){
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(color = Color.White)){
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(254, 194, 197))){
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .width(8.dp)
                            .background(color = Color(255, 159, 140)))
                        Text(
                            text = "Movies",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(5.dp, 0.dp, 0.dp, 0.dp)
                                .weight(1f),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            color = Color(252,87,94)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.triangle_go_to_icon),
                            contentDescription = "",
                            Modifier
                                .size(25.dp)
                                .scale(1f)
                                .align(Alignment.CenterVertically)
                                .padding(5.dp, 0.dp)
                                .clickable {
                                    //TODO
                                }
                        )

                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(color = Color.White)){
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(218, 241, 255))){
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .width(8.dp)
                            .background(color = Color(188, 230, 255)))
                        Text(
                            text = "TV Shows",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(5.dp, 0.dp, 0.dp, 0.dp)
                                .weight(1f),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            color = Color(68,187,255)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.triangle_go_to_icon),
                            contentDescription = "",
                            Modifier
                                .size(25.dp)
                                .scale(1f)
                                .align(Alignment.CenterVertically)
                                .padding(5.dp, 0.dp)
                                .clickable {
                                    //TODO
                                }
                        )

                    }
                }
                Spacer(modifier = Modifier.width(10.dp))

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(color = Color.White)){
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(231, 246, 218))){
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .width(8.dp)
                            .background(color = Color(215, 239, 195)))
                        Text(
                            text = "Actors",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(5.dp, 0.dp, 0.dp, 0.dp)
                                .weight(1f),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            color = Color(116,214,31)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.triangle_go_to_icon),
                            contentDescription = "",
                            Modifier
                                .size(25.dp)
                                .scale(1f)
                                .align(Alignment.CenterVertically)
                                .padding(5.dp, 0.dp)
                                .clickable {
                                    //TODO
                                }
                        )

                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            //SMALL TAGS
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)){

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .background(color = Color(254, 194, 197)),
                        contentAlignment = Alignment.Center,

                    ){
                            Text(
                                text = "#Spring 2025",
                                modifier = Modifier.padding(8.dp, 0.dp),
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                                color = Color(252,87,94)
                            )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .background(color = Color(218, 241, 255)),
                        contentAlignment = Alignment.Center,

                        ){
                        Text(
                            text = "#Winter 2025",
                            modifier = Modifier.padding(8.dp, 0.dp),
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            color =  Color(68,187,255)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .background(color = Color(231, 246, 218)),
                        contentAlignment = Alignment.Center,

                        ){
                        Text(
                            text = "#2025",
                            modifier = Modifier.padding(8.dp, 0.dp),
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            color = Color(116,214,31)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .background(color = Color(254, 194, 197)),
                        contentAlignment = Alignment.Center,

                        ){
                        Text(
                            text = "#2024",
                            modifier = Modifier.padding(8.dp, 0.dp),
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            color = Color(252,87,94)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)){

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .background(color = Color(218, 241, 255)),
                        contentAlignment = Alignment.Center,

                        ){
                        Text(
                            text = "#Upcoming",
                            modifier = Modifier.padding(8.dp, 0.dp),
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            color = Color(68,187,255)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .background(color = Color(231, 246, 218)),
                        contentAlignment = Alignment.Center,

                        ){
                        Text(
                            text = "#Most popular",
                            modifier = Modifier.padding(8.dp, 0.dp),
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                            color = Color(116,214,31)
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            //NEWS
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = Color.White)){
                Row(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(255, 232, 216))){
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .width(8.dp)
                        .background(color = Color(255, 213, 184)))
                    Text(
                        text = "NEWS",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            .weight(1f),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                        color = Color(255, 191, 95)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            News()

        }

    }
}
