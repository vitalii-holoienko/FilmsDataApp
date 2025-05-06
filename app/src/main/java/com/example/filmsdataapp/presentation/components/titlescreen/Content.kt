package com.example.filmsdataapp.presentation.components.titlescreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color.rgb
import android.graphics.drawable.BitmapDrawable
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.utils.NumsFormatter
import com.example.filmsdataapp.presentation.utils.TimeFormatter
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.LinksColor
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun Content(title : Title) {
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)
    val listOfReviews by viewModel.reviewsToDisplay.observeAsState(emptyList())
    viewModel.getTitleReviews(title.id!!)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackGroundColor)
        .verticalScroll(rememberScrollState())
        .padding(10.dp)){
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = title.primaryTitle!!,
            color = TextColor,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
            modifier = Modifier.padding(0.dp, 8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.width(IntrinsicSize.Max)) {
            Image(
                painter = rememberAsyncImagePainter(title.primaryImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .width(230.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(30.dp))

            Box(modifier = Modifier
                .height(35.dp)
                .fillMaxWidth()
                .background(color = Color.White)
            ){
                Row(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(218, 241, 255))){
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .width(8.dp)
                        .background(color = Color(188, 230, 255)))
                    Text(
                        text = "Add to the list",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            .weight(1f),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                        color = Color(23,96,161)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.triangle_go_to_icon),
                        contentDescription = "",
                        Modifier
                            .size(25.dp)
                            .scale(1f)
                            .align(Alignment.CenterVertically)
                            .padding(5.dp, 0.dp)

                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(color = Color.White)){
            Row(modifier = Modifier
                .fillMaxSize()
                .background(color = Color(rgb(49, 50, 50)))){
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .background(color = Color(rgb(42, 44, 43))))
                Text(
                    text = "Information",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .weight(1f),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Column{
            Text(
                text = "Type: ${title.type?.replaceFirstChar{it.uppercaseChar()}}",
                color = Color(rgb(232,235,239)),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                modifier = Modifier.padding(0.dp,4.dp)
            )
            Text(
                text = "Runtime: ${TimeFormatter.formatDuration(title.runtimeMinutes)}",
                color = Color(rgb(232,235,239)),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                modifier = Modifier.padding(0.dp,4.dp)
            )
            Text(
                text = "Release Date: ${title.releaseDate?.replace("-", ".")}",
                color = Color(rgb(232,235,239)),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                modifier = Modifier.padding(0.dp,4.dp)
            )
            Text(
                text = "Genres: ${title.genres}",
                color = Color(rgb(232,235,239)),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                modifier = Modifier.padding(0.dp,4.dp)
            )
            Text(
                text = "Content rating: ${title.contentRating}",
                color = Color(rgb(232,235,239)),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                modifier = Modifier.padding(0.dp,4.dp)
            )
            Text(
                text = "Budget: ${NumsFormatter.formatMillions(title.budget)}",
                color = Color(rgb(232,235,239)),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                modifier = Modifier.padding(0.dp,4.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(color = Color.White)){
            Row(modifier = Modifier
                .fillMaxSize()
                .background(color = Color(rgb(49, 50, 50)))){
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .background(color = Color(rgb(42, 44, 43))))
                Text(
                    text = "Rating",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .weight(1f),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }
        }

        val stars = title.averageRating!! / 2
        val fullStars = stars.toInt()
        val hasHalfStar = (stars - fullStars) >= 0.25
        Spacer(modifier= Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            repeat(fullStars) {
                Image(
                    painter = painterResource(id = R.drawable.star_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(3.dp)
                )

            }
            repeat(5 - fullStars) {
                Image(
                    painter = painterResource(id = R.drawable.grey_star_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(3.dp)
                )
            }
            Text(
                text = title.averageRating.toString(),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(5.dp, 0.dp, 0.dp, 0.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                color = TextColor
            )

        }
        Spacer(modifier= Modifier.height(20.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(color = Color.White)){
            Row(modifier = Modifier
                .fillMaxSize()
                .background(color = Color(rgb(49, 50, 50)))){
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .background(color = Color(rgb(42, 44, 43))))
                Text(
                    text = "Description",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .weight(1f),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier=Modifier.fillMaxWidth()){
            Text(
                text = title.description.toString(),
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                color = TextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(color = Color.White)){
            Row(modifier = Modifier
                .fillMaxSize()
                .background(color = Color(rgb(49, 50, 50)))){
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .background(color = Color(rgb(42, 44, 43))))
                Text(
                    text = "Reviews",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .weight(1f),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        listOfReviews.forEach{
            Column(modifier = Modifier.fillMaxWidth().background(color = Color(rgb(49, 50, 50)))){
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = it.nickName!!,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),

                        color = LinksColor,
                        modifier = Modifier.padding(5.dp, 0.dp)
                    )
                    Box(modifier = Modifier.fillMaxWidth().weight(1f))

                    Text(
                        text = it.upvotes.toString(),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                        color = TextColor,

                    )
                    Image(
                        painter = painterResource(id = R.drawable.like_icon),
                        contentDescription = "",
                        Modifier
                            .size(35.dp)
                            .scale(1f)
                            .align(Alignment.CenterVertically)


                    )
                    Spacer(modifier=Modifier.width(5.dp))
                    Text(
                        text = it.downvotes.toString(),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                        color = TextColor,

                        )
                    Image(
                        painter = painterResource(id = R.drawable.dislike_icon),
                        contentDescription = "",
                        Modifier
                            .size(35.dp)
                            .scale(1f)
                            .align(Alignment.CenterVertically)


                    )

                }
                Text(
                    text = it.summary!!,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    color = TextColor,
                    modifier = Modifier.padding(5.dp)

                    )

                Text(
                    text = it.originalText!!,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor,
                    modifier = Modifier.padding(5.dp)

                )
            }


            Spacer(modifier = Modifier.height(25.dp))
        }



    }
}

