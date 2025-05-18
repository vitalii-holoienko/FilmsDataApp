package com.example.filmsdataapp.presentation.components.mainscreen.content

import Movie
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.filmsdataapp.domain.model.Title
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(movies : List<Title>, navigateToTitleScreen: (Title) -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 20.dp
    val imageWidth = (screenWidth - (2 * 10.dp)) / 3

    val listState = rememberLazyListState()


    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        flingBehavior = rememberSnapFlingBehavior(listState)
    ) {
        items(8) { index ->
            if(movies.isNotEmpty()){
                val movie = movies[index]
                ImageItem(imageWidth, movie, navigateToTitleScreen)
            }else{
                ImageItem(imageWidth, null, navigateToTitleScreen)
            }

        }
    }
}

