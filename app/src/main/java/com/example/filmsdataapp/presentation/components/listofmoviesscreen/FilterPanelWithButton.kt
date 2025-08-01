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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.FilterOption
import com.example.filmsdataapp.domain.model.Genre
import com.example.filmsdataapp.domain.model.SORTED_BY
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor
import kotlin.math.roundToInt



@Composable
fun FilterPanelWithButton(
    isFilterVisible: Boolean,
    onToggle: () -> Unit,
    viewModel : MainActivityViewModel
) {
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

                    FilterSection(
                        title = "Sorted by",
                        options = listOf(
                            FilterOption("Rating", { viewModel.filterStatus.sortedBy = SORTED_BY.RATING }, { viewModel.filterStatus.sortedBy = null }),
                            FilterOption("Popularity", { viewModel.filterStatus.sortedBy = SORTED_BY.POPULARITY }, { viewModel.filterStatus.sortedBy = null }),
                            FilterOption("Release date", { viewModel.filterStatus.sortedBy = SORTED_BY.RELEASE_DATE }, { viewModel.filterStatus.sortedBy = null }),
                            FilterOption("Random", { viewModel.filterStatus.sortedBy = SORTED_BY.RANDOM }, { viewModel.filterStatus.sortedBy = null }),
                            FilterOption("Alphabet", { viewModel.filterStatus.sortedBy = SORTED_BY.ALPHABET }, { viewModel.filterStatus.sortedBy = null }),
                        ),
                        true
                    )

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
                        ),
                        true
                    )

                    FilterSection(
                        title = "Rating",
                        options = listOf(
                            FilterOption("8+", { viewModel.filterStatus.averageRationFrom = 8 }, { viewModel.filterStatus.averageRationFrom = null }),
                            FilterOption("7+", { viewModel.filterStatus.averageRationFrom = 7 }, { viewModel.filterStatus.averageRationFrom = null }),
                            FilterOption("6+", { viewModel.filterStatus.averageRationFrom = 6 }, { viewModel.filterStatus.averageRationFrom = null }),
                        ),
                        true
                    )

                    FilterSection(
                        title = "Genres",
                        options = listOf(
                            FilterOption("Drama", { viewModel.filterStatus.genre?.add(Genre.DRAMA) }, { viewModel.filterStatus.genre?.remove(Genre.DRAMA) }),
                            FilterOption("Comedy", { viewModel.filterStatus.genre?.add(Genre.COMEDY) }, { viewModel.filterStatus.genre?.remove(Genre.COMEDY) }),
                            FilterOption("Documentary", { viewModel.filterStatus.genre?.add(Genre.DOCUMENTARY) }, { viewModel.filterStatus.genre?.remove(Genre.DOCUMENTARY) }),
                            FilterOption("Action", { viewModel.filterStatus.genre?.add(Genre.ACTION) }, { viewModel.filterStatus.genre?.remove(Genre.ACTION) }),
                            FilterOption("Romance", { viewModel.filterStatus.genre?.add(Genre.ROMANCE) }, { viewModel.filterStatus.genre?.remove(Genre.ROMANCE) }),
                            FilterOption("Thriller", { viewModel.filterStatus.genre?.add(Genre.THRILLER) }, { viewModel.filterStatus.genre?.remove(Genre.THRILLER) }),
                            FilterOption("Crime", { viewModel.filterStatus.genre?.add(Genre.CRIME) }, { viewModel.filterStatus.genre?.remove(Genre.CRIME) }),
                            FilterOption("Horror", { viewModel.filterStatus.genre?.add(Genre.HORROR) }, { viewModel.filterStatus.genre?.remove(Genre.HORROR) }),
                            FilterOption("Adventure", { viewModel.filterStatus.genre?.add(Genre.ADVENTURE) }, { viewModel.filterStatus.genre?.remove(Genre.ADVENTURE) }),
                            FilterOption("Family", { viewModel.filterStatus.genre?.add(Genre.FAMILY) }, { viewModel.filterStatus.genre?.remove(Genre.FAMILY) }),
                            FilterOption("Animation", { viewModel.filterStatus.genre?.add(Genre.ANIMATION) }, { viewModel.filterStatus.genre?.remove(Genre.ADVENTURE) }),
                            FilterOption("Reality-TV", { viewModel.filterStatus.genre?.add(Genre.REALITY_TV) }, { viewModel.filterStatus.genre?.remove(Genre.REALITY_TV) }),
                            FilterOption("Mystery", { viewModel.filterStatus.genre?.add(Genre.MYSTERY) }, { viewModel.filterStatus.genre?.remove(Genre.MYSTERY) }),
                            FilterOption("Fantasy", { viewModel.filterStatus.genre?.add(Genre.FANTASY) }, { viewModel.filterStatus.genre?.remove(Genre.FANTASY) }),
                            FilterOption("History", { viewModel.filterStatus.genre?.add(Genre.HISTORY) }, { viewModel.filterStatus.genre?.remove(Genre.HISTORY) }),
                            FilterOption("Biography", { viewModel.filterStatus.genre?.add(Genre.BIOGRAPHY) }, { viewModel.filterStatus.genre?.remove(Genre.BIOGRAPHY) }),
                            FilterOption("Sci-fi", { viewModel.filterStatus.genre?.add(Genre.SCI_FI) }, { viewModel.filterStatus.genre?.remove(Genre.SCI_FI) }),
                            FilterOption("Sport", { viewModel.filterStatus.genre?.add(Genre.SPORT) }, { viewModel.filterStatus.genre?.remove(Genre.SPORT) }),
                            FilterOption("Adult", { viewModel.filterStatus.genre?.add(Genre.ADULT) }, { viewModel.filterStatus.genre?.remove(Genre.ADULT) }),
                            FilterOption("War", { viewModel.filterStatus.genre?.add(Genre.WAR) }, { viewModel.filterStatus.genre?.remove(Genre.WAR) }),
                        ),
                        false
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