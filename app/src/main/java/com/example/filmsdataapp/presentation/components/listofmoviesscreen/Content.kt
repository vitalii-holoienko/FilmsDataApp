package com.example.filmsdataapp.presentation.components.listofmoviesscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.LinksColor
import com.example.filmsdataapp.ui.theme.TextColor


@SuppressLint("SuspiciousIndentation")
@Composable
fun Content(from : String, navigateToTitleScreen: (Title) -> Unit){
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)
    var pageName by remember(from) { mutableStateOf("") }
    var pageDescription by remember(from) { mutableStateOf("") }
    var typeContentToDisplay by remember(from) { mutableStateOf("") }

    LaunchedEffect(key1 = from) {
        when (from) {
            "Currently Trending" -> {
                pageName = "Currently Trending"
                pageDescription = "This page displays currently popular movies,\nsorted by rating"
                typeContentToDisplay = "Movies"
                viewModel._inititalTitleToDisplay.value = viewModel.currentlyTrendingMovies.value
                viewModel._titlesToDisplay.value = viewModel.currentlyTrendingMovies.value
            }
            "Coming soon" -> {
                pageName = "Coming soon"
                pageDescription = "This page displays coming soon movies"
                typeContentToDisplay = "Movies"
                viewModel._inititalTitleToDisplay.value = viewModel.comingSoonMovies.value
                viewModel._titlesToDisplay.value = viewModel.comingSoonMovies.value
            }
            "Movies" -> {
                pageName = "Movies"
                pageDescription = "This page displays list of movies,\nsorted by rating"
                typeContentToDisplay = "Movies"
                viewModel._inititalTitleToDisplay.value = viewModel.mostPopularMovies.value
                viewModel._titlesToDisplay.value = viewModel.mostPopularMovies.value
            }
            "TVShows" -> {
                pageName = "TVShows"
                pageDescription = "This page displays list of TV shows,\nsorted by rating"
                typeContentToDisplay = "TVShows"
                viewModel._inititalTitleToDisplay.value = viewModel.mostPopularTVShows.value
                viewModel._titlesToDisplay.value = viewModel.mostPopularTVShows.value

            }
            "Actors" ->{
                pageName = "Actors"
                pageDescription = "This page displays list of actors,\nsorted in alphabetical order"
                typeContentToDisplay = "Actors"
            }


        }
    }


//    if(from == "Actors"){
//        pageName = "Actors"
//        pageDescription = "This page displays list of actors,\nsorted in alphabetical order"
//        typeContentToDisplay = "Actors"
//    }

//    val listOfMovies by viewModel.titlesToDisplay.observeAsState(emptyList())
//    val listOfTVShows by viewModel.titlesToDisplay.observeAsState(emptyList())
    val listOfActors by viewModel.actors.observeAsState(emptyList())
    val listOfTitlesToDisplay by viewModel.titlesToDisplay.observeAsState(emptyList())
    Log.d("TEKKEN", pageName)

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val totalHorizontalPadding = 10.dp * 3
    val imageWidth = (screenWidth - totalHorizontalPadding) / 2

//    val MovieRows = listOfMovies.chunked(2)
//    val TVShowsRows = listOfTVShows.chunked(2)
    val ActorsRows = listOfActors.chunked(2)

    val titlesRows = listOfTitlesToDisplay.chunked(2)
    var isFilterVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(BackGroundColor)
    ){


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
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                item {
                    Column() {
                        Spacer(modifier = Modifier.height(20.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        ) {
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
                    }
                }
                if(listOfTitlesToDisplay != viewModel.actors){
                    items(titlesRows) { movieRow ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            movieRow.forEach { m ->
                                Column {
                                    Image(
                                        painter = rememberAsyncImagePainter(m.primaryImage),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(imageWidth)
                                            .height(250.dp)
                                            .clickable {
                                                navigateToTitleScreen(m)
                                            }
                                    )
                                    Text(
                                        text = m.originalTitle ?: "",
                                        color = LinksColor,
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier
                                            .width(imageWidth)
                                            .padding(5.dp, 3.dp)
                                    )
                                    Row(modifier = Modifier.width(imageWidth)) {
                                        Text(
                                            text = m.type!!,
                                            color = TextColor,
                                            fontSize = 10.sp,
                                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.padding(5.dp, 3.dp)
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(
                                            text = m.startYear.toString(),
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
                            if (movieRow.size == 1) {
                                Spacer(modifier = Modifier.width(imageWidth))
                            }
                        }
                    }
                }
                else{
                    items(ActorsRows) { actorsRow ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            actorsRow.forEach { m ->
                                Column {
                                    Image(
                                        painter = rememberAsyncImagePainter(m.primaryImage!!.url),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(imageWidth)
                                            .height(250.dp)
                                    )
                                    Text(
                                        text = m.nameText!!.text ?: "",
                                        color = LinksColor,
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier
                                            .width(imageWidth)
                                            .padding(5.dp, 3.dp)
                                    )
//                                    Row(modifier = Modifier.width(imageWidth)) {
//                                        Text(
//                                            text = "Actor",
//                                            color = TextColor,
//                                            fontSize = 10.sp,
//                                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
//                                            maxLines = 1,
//                                            overflow = TextOverflow.Ellipsis,
//                                            modifier = Modifier.padding(5.dp, 3.dp)
//                                        )
//                                        Spacer(modifier = Modifier.weight(1f))
//                                        Text(
//                                            text = m.,
//                                            color = TextColor,
//                                            fontSize = 10.sp,
//                                            fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
//                                            maxLines = 1,
//                                            overflow = TextOverflow.Ellipsis,
//                                            modifier = Modifier.padding(5.dp, 3.dp)
//                                        )
//                                    }
                                }
                            }
                            if (actorsRow.size == 1) {
                                Spacer(modifier = Modifier.width(imageWidth))
                            }
                        }
                    }
                }
            }
        FilterPanelWithButton(
            isFilterVisible = isFilterVisible,
            onToggle = { isFilterVisible = !isFilterVisible },

        )

        }


}
