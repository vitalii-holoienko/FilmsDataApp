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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.Text
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.LinksColor
import com.example.filmsdataapp.ui.theme.TextColor
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun Content(from : String, viewModel : MainActivityViewModel){
    var pageName by remember(from) { mutableStateOf("") }
    var pageDescription by remember(from) { mutableStateOf("") }
    var typeContentToDisplay by remember(from) { mutableStateOf("") }

    var searchedQuery = viewModel.searchedQuery.observeAsState("")
    var recievedActorInfo = viewModel.recievedActorInfo.observeAsState(false)
    var showActors by remember {
        mutableStateOf(false)
    }
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
                pageDescription = "This page displays list of top 250 movies,\naccording to IMDB "
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
                showActors = true
            }
            "Searched" ->{
                pageName = "Titles Search"
                pageDescription = "Titles found for the query \"${searchedQuery.value}\""
                typeContentToDisplay = "-"
                viewModel._inititalTitleToDisplay.value = viewModel.searchedTitles.value
                viewModel._titlesToDisplay.value = viewModel.searchedTitles.value
            }
        }
    }

    LaunchedEffect(recievedActorInfo.value) {
        if (recievedActorInfo.value!!) {
            viewModel.onActorInfoClicked()
            viewModel.recievedActorInfo.value = false
        }
    }

//    val listOfMovies by viewModel.titlesToDisplay.observeAsState(emptyList())
//    val listOfTVShows by viewModel.titlesToDisplay.observeAsState(emptyList())

    val listOfActors by viewModel.actors.observeAsState(emptyList())
    val listOfTitlesToDisplay by viewModel.titlesToDisplay.observeAsState(emptyList())


    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val totalHorizontalPadding = 10.dp * 3
    val imageWidth = (screenWidth - totalHorizontalPadding) / 2
    val ActorsRows = listOfActors.chunked(2)
    var titlesRows = emptyList<List<Title>>()
    if(listOfTitlesToDisplay!=null) titlesRows = listOfTitlesToDisplay.chunked(2)

    var isFilterVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(BackGroundColor)
    ) {
        if (listOfTitlesToDisplay != null) {
            if (!showActors) {
                listOfTitlesToDisplay?.let { list ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(30.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        items(list) { m ->
                            Column(
                                modifier = Modifier.clickable {
                                    viewModel.onTitleClicked(m)
                                }
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(m.primaryImage),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                )
                                Text(
                                    text = m.originalTitle ?: "",
                                    color = LinksColor,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(5.dp, 3.dp)
                                )
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = m.type?.replaceFirstChar { it.uppercase() } ?: "",
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
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {

                    items(ActorsRows) { actorsRow ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            actorsRow.forEach { m ->
                                Column(modifier = Modifier.clickable {
                                    viewModel.getActorInfo(m.id)
                                }) {
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
                                }
                            }
                            if (actorsRow.size == 1) {
                                Spacer(modifier = Modifier.width(imageWidth))
                            }
                        }
                    }
                }
            }
        } else {
            // твой "No Internet Connection" экран
        }

        if (from != "Actors") {
            FilterPanelWithButton(
                isFilterVisible = isFilterVisible,
                onToggle = { isFilterVisible = !isFilterVisible },
                viewModel
            )
        }
    }


}
