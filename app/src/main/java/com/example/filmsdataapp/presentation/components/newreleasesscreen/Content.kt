package com.example.filmsdataapp.presentation.components.newreleasesscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
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
import java.security.AlgorithmConstraints

@Composable
fun Content(){
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
            .background(Color(36,36,36))
    ){


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .offset(x = 25.dp, y = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color(33, 33, 33))
                .width(60.dp)
                .height(85.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    //TODO
                }
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .graphicsLayer {
                        rotationZ = -90f
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Image(
                    painter = painterResource(id = R.drawable.filter_arrow_2_left),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp).graphicsLayer {
                        rotationZ = -270f
                    }.padding(0.dp,0.dp,40.dp,0.dp).wrapContentWidth(unbounded = true)
                )
            }
        }

        Column(){
            Spacer(modifier= Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth().height(120.dp).padding(10.dp, 0.dp)){
                Text(
                    text = "New Releases",
                    color = Color.White,
                    fontSize = 27.sp,
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "This page displays recently released movies,\nsorted by rating",
                    color = Color.White,
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
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(imageWidth)
                                    .height(250.dp)
                            )

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