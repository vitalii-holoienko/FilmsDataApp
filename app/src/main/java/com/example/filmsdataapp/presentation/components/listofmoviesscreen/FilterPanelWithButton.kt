package com.example.filmsdataapp.presentation.components.listofmoviesscreen

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.FilterOption
import com.example.filmsdataapp.domain.model.Genre
import com.example.filmsdataapp.domain.model.SORTED_BY
import com.example.filmsdataapp.domain.model.Type
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor
import kotlin.math.roundToInt



@Composable
fun FilterPanelWithButton(
    isFilterVisible: Boolean,
    onToggle: () -> Unit,
) {
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)
    val filterWidth = 300.dp
    val buttonWidth = 40.dp
    val density = LocalDensity.current

    val offsetX by animateFloatAsState(
        targetValue = if (isFilterVisible) 0f else with(density) { filterWidth.toPx() },
        animationSpec = tween(durationMillis = 300),
        label = "offsetX"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .width(filterWidth + buttonWidth)
                .align(Alignment.TopEnd)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(filterWidth)
                    .background(Color.White)
                    .shadow(8.dp)
                    .align(Alignment.CenterEnd)
                    .background(color = PrimaryColor)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text("Filters", style = MaterialTheme.typography.titleMedium, color = TextColor)

                    Spacer(modifier = Modifier.height(8.dp))

                    // Тип (Movie / TV Show)
                    FilterSection(
                        title = "Type",
                        options = listOf(
                            FilterOption("Movie", { viewModel.filterStatus.type = Type.MOVIE }, { viewModel.filterStatus.type = null }),
                            FilterOption("TV Show", { viewModel.filterStatus.type = Type.TVSHOW }, { viewModel.filterStatus.type = null }),
                        )
                    )

                    // Сортировка
                    FilterSection(
                        title = "Sorted by",
                        options = listOf(
                            FilterOption("Rating", { viewModel.filterStatus.sortedBy = SORTED_BY.RATING }, { viewModel.filterStatus.sortedBy = null }),
                            FilterOption("Popularity", { viewModel.filterStatus.sortedBy = SORTED_BY.POPULARITY }, { viewModel.filterStatus.sortedBy = null }),
                            FilterOption("Release date", { viewModel.filterStatus.sortedBy = SORTED_BY.RELEASE_DATE }, { viewModel.filterStatus.sortedBy = null }),
                            FilterOption("Random", { viewModel.filterStatus.sortedBy = SORTED_BY.RANDOM }, { viewModel.filterStatus.sortedBy = null }),
                            FilterOption("Alphabet", { viewModel.filterStatus.sortedBy = SORTED_BY.ALPHABET }, { viewModel.filterStatus.sortedBy = null }),
                        )
                    )

                    // Даты выхода
                    FilterSection(
                        title = "Release Date",
                        options = listOf(
                            FilterOption("2025", {
                                viewModel.filterStatus.dateOfReleaseFrom = 2025
                                viewModel.filterStatus.dateOfReleaseTo = 2025
                            }, {
                                viewModel.filterStatus.dateOfReleaseFrom = null
                                viewModel.filterStatus.dateOfReleaseTo = null
                            }),
                            FilterOption("2024", {
                                viewModel.filterStatus.dateOfReleaseFrom = 2024
                                viewModel.filterStatus.dateOfReleaseTo = 2024
                            }, {
                                viewModel.filterStatus.dateOfReleaseFrom = null
                                viewModel.filterStatus.dateOfReleaseTo = null
                            }),
                            FilterOption("2022-2023", {
                                viewModel.filterStatus.dateOfReleaseFrom = 2022
                                viewModel.filterStatus.dateOfReleaseTo = 2023
                            }, {
                                viewModel.filterStatus.dateOfReleaseFrom = null
                                viewModel.filterStatus.dateOfReleaseTo = null
                            }),
                            FilterOption("2017-2021", {
                                viewModel.filterStatus.dateOfReleaseFrom = 2017
                                viewModel.filterStatus.dateOfReleaseTo = 2021
                            }, {
                                viewModel.filterStatus.dateOfReleaseFrom = null
                                viewModel.filterStatus.dateOfReleaseTo = null
                            }),
                            FilterOption("2010-2016", {
                                viewModel.filterStatus.dateOfReleaseFrom = 2010
                                viewModel.filterStatus.dateOfReleaseTo = 2016
                            }, {
                                viewModel.filterStatus.dateOfReleaseFrom = null
                                viewModel.filterStatus.dateOfReleaseTo = null
                            }),
                            FilterOption("2000-2010", {
                                viewModel.filterStatus.dateOfReleaseFrom = 2000
                                viewModel.filterStatus.dateOfReleaseTo = 2010
                            }, {
                                viewModel.filterStatus.dateOfReleaseFrom = null
                                viewModel.filterStatus.dateOfReleaseTo = null
                            }),
                            FilterOption("Older", {
                                viewModel.filterStatus.dateOfReleaseTo = 2000
                            }, {
                                viewModel.filterStatus.dateOfReleaseTo = null
                            }),
                        )
                    )

                    // Рейтинг
                    FilterSection(
                        title = "Rating",
                        options = listOf(
                            FilterOption("8+", { viewModel.filterStatus.averageRationFrom = 8 }, { viewModel.filterStatus.averageRationFrom = null }),
                            FilterOption("7+", { viewModel.filterStatus.averageRationFrom = 7 }, { viewModel.filterStatus.averageRationFrom = null }),
                            FilterOption("6+", { viewModel.filterStatus.averageRationFrom = 6 }, { viewModel.filterStatus.averageRationFrom = null }),
                        )
                    )

                    // Жанры
                    FilterSection(
                        title = "Genres",
                        options = listOf(
                            FilterOption("Drama", { viewModel.filterStatus.genre = Genre.DRAMA }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Comedy", { viewModel.filterStatus.genre = Genre.COMEDY }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Documentary", { viewModel.filterStatus.genre = Genre.DOCUMENTARY }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Action", { viewModel.filterStatus.genre = Genre.ACTION }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Romance", { viewModel.filterStatus.genre = Genre.ROMANCE }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Thriller", { viewModel.filterStatus.genre = Genre.THRILLER }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Crime", { viewModel.filterStatus.genre = Genre.CRIME }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Horror", { viewModel.filterStatus.genre = Genre.HORROR }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Adventure", { viewModel.filterStatus.genre = Genre.ADVENTURE }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Family", { viewModel.filterStatus.genre = Genre.FAMILY }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Animation", { viewModel.filterStatus.genre = Genre.ANIMATION }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Reality-TV", { viewModel.filterStatus.genre = Genre.REALITY_TV }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Mystery", { viewModel.filterStatus.genre = Genre.MYSTERY }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Fantasy", { viewModel.filterStatus.genre = Genre.FANTASY }, { viewModel.filterStatus.genre = null }),
                            FilterOption("History", { viewModel.filterStatus.genre = Genre.HISTORY }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Biography", { viewModel.filterStatus.genre = Genre.BIOGRAPHY }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Sci-fi", { viewModel.filterStatus.genre = Genre.SCI_FI }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Sport", { viewModel.filterStatus.genre = Genre.SPORT }, { viewModel.filterStatus.genre = null }),
                            FilterOption("Adult", { viewModel.filterStatus.genre = Genre.ADULT }, { viewModel.filterStatus.genre = null }),
                            FilterOption("War", { viewModel.filterStatus.genre = Genre.WAR }, { viewModel.filterStatus.genre = null }),
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.applyFilter() },
                        modifier = Modifier.align(Alignment.CenterHorizontally).width(200.dp)
                    ) {
                        Text("Apply")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .width(buttonWidth)
                    .height(80.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                    .background(PrimaryColor)
                    .clickable { onToggle() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_arrow_2_left),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}