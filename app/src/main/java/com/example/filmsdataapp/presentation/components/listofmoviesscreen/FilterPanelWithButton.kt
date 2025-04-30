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
                Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
                    Text("Filters", style = MaterialTheme.typography.titleMedium, color = TextColor)
                    Spacer(modifier = Modifier.height(8.dp))
                    Column {

                        Column {
                            Text("Type", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(12.dp), color = TextColor)
                            val options1 = listOf(
                                FilterOption("Movie") { viewModel.filterStatus.type = Type.MOVIE },
                                FilterOption("TV Show") { viewModel.filterStatus.type = Type.TVSHOW },
                            )
                            var selectedIndex1 by remember { mutableStateOf<Int?>(null) }

                            Column {
                                options1.forEachIndexed { index, option ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(vertical = 0.dp)
                                            .clickable {
                                                selectedIndex1 = index
                                                option.onSelected()
                                            }
                                    ) {
                                        Checkbox(
                                            checked = selectedIndex1 == index,
                                            onCheckedChange = {
                                                selectedIndex1 = if (it) index else null
                                                if (it) option.onSelected()
                                            },

                                            )
                                        Text(option.text, color = TextColor)
                                    }
                                }
                            }
                            Box(modifier = Modifier.fillMaxSize().background(color = BackGroundColor))
                            Text("Sorted by", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(12.dp),color = TextColor)
                            val options2 = listOf(
                                FilterOption("Rating") { viewModel.filterStatus.sortedBy = SORTED_BY.RATING },
                                FilterOption("Popularity") { viewModel.filterStatus.sortedBy = SORTED_BY.POPULARITY },
                                FilterOption("Release date") { viewModel.filterStatus.sortedBy = SORTED_BY.RELEASE_DATE },
                                FilterOption("Random") { viewModel.filterStatus.sortedBy = SORTED_BY.RANDOM },
                                FilterOption("Alphabet") { viewModel.filterStatus.sortedBy = SORTED_BY.ALPHABET },
                            )
                            var selectedIndex2 by remember { mutableStateOf<Int?>(null) }

                            Column {
                                options2.forEachIndexed { index, option ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(vertical = 0.dp)
                                            .clickable {
                                                selectedIndex2 = index
                                                option.onSelected()
                                            }
                                    ) {
                                        Checkbox(
                                            checked = selectedIndex2 == index,
                                            onCheckedChange = {
                                                selectedIndex2 = if (it) index else null
                                                if (it) option.onSelected()
                                            }
                                        )
                                        Text(option.text, color = TextColor)
                                    }
                                }
                            }

                            Text("Release Date", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(12.dp), color = TextColor)
                            val options3 = listOf(
                                FilterOption("2025") { viewModel.filterStatus.dateOfReleaseFrom = 2025;viewModel.filterStatus.dateOfReleaseTo = 2025; },
                                FilterOption("2024") { viewModel.filterStatus.dateOfReleaseFrom = 2024;viewModel.filterStatus.dateOfReleaseTo = 2024; },
                                FilterOption("2022-2023") { viewModel.filterStatus.dateOfReleaseFrom = 2022;viewModel.filterStatus.dateOfReleaseTo = 2023; },
                                FilterOption("2017-2021") { viewModel.filterStatus.dateOfReleaseFrom = 2017;viewModel.filterStatus.dateOfReleaseTo = 2021; },
                                FilterOption("2010-2016") { viewModel.filterStatus.dateOfReleaseFrom = 2010;viewModel.filterStatus.dateOfReleaseTo = 2016; },
                                FilterOption("2000-2010") { viewModel.filterStatus.dateOfReleaseFrom = 2000;viewModel.filterStatus.dateOfReleaseTo = 2010; },
                                FilterOption("Older") { viewModel.filterStatus.dateOfReleaseTo = 2000; },
                            )
                            var selectedIndex3 by remember { mutableStateOf<Int?>(null) }

                            Column {
                                options3.forEachIndexed { index, option ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(vertical = 0.dp)
                                            .clickable {
                                                selectedIndex3 = index
                                                option.onSelected()
                                            }
                                    ) {
                                        Checkbox(
                                            checked = selectedIndex3 == index,
                                            onCheckedChange = {
                                                selectedIndex3 = if (it) index else null
                                                if (it) option.onSelected()
                                            }
                                        )
                                        Text(option.text, color = TextColor)
                                    }
                                }
                            }

                            Text("Rating", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(12.dp), color = TextColor)
                            val options4 = listOf(
                                FilterOption("8+") { viewModel.filterStatus.averageRationFrom = 8 },
                                FilterOption("7+") { viewModel.filterStatus.averageRationFrom = 7 },
                                FilterOption("6+") { viewModel.filterStatus.averageRationFrom = 6 },
                            )
                            var selectedIndex4 by remember { mutableStateOf<Int?>(null) }

                            Column {
                                options4.forEachIndexed { index, option ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(vertical = 0.dp)
                                            .clickable {
                                                selectedIndex4 = index
                                                option.onSelected()
                                            }
                                    ) {
                                        Checkbox(
                                            checked = selectedIndex4 == index,
                                            onCheckedChange = {
                                                selectedIndex4 = if (it) index else null
                                                if (it) option.onSelected()
                                            }
                                        )
                                        Text(option.text, color = TextColor)
                                    }
                                }
                            }

                            Text("Genres", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(12.dp), color = TextColor)
                            val options5 = listOf(
                                FilterOption("Drama") { viewModel.filterStatus.genre = Genre.DRAMA},
                                FilterOption("Comedy") { viewModel.filterStatus.genre = Genre.COMEDY },
                                FilterOption("Documentary") { viewModel.filterStatus.genre = Genre.DOCUMENTARY},
                                FilterOption("Action") { viewModel.filterStatus.genre = Genre.ACTION },
                                FilterOption("Romance") { viewModel.filterStatus.genre = Genre.ROMANCE },
                                FilterOption("Thriller") { viewModel.filterStatus.genre = Genre.THRILLER },
                                FilterOption("Crime") { viewModel.filterStatus.genre = Genre.CRIME },
                                FilterOption("Horror") { viewModel.filterStatus.genre = Genre.HORROR },
                                FilterOption("Adventure") { viewModel.filterStatus.genre = Genre.ADVENTURE},
                                FilterOption("Family") { viewModel.filterStatus.genre = Genre.FAMILY },
                                FilterOption("Animation") { viewModel.filterStatus.genre = Genre.ANIMATION },
                                FilterOption("Reality-TV") { viewModel.filterStatus.genre = Genre.REALITY_TV },
                                FilterOption("Mystery") { viewModel.filterStatus.genre = Genre.MYSTERY },
                                FilterOption("Fantasy") { viewModel.filterStatus.genre = Genre.FANTASY },
                                FilterOption("History") { viewModel.filterStatus.genre = Genre.HISTORY },
                                FilterOption("Biography") { viewModel.filterStatus.genre = Genre.BIOGRAPHY },
                                FilterOption("Sci-fi") { viewModel.filterStatus.genre = Genre.SCI_FI },
                                FilterOption("Sport") {viewModel.filterStatus.genre = Genre.SPORT },
                                FilterOption("Adult") { viewModel.filterStatus.genre = Genre.ADULT },
                                FilterOption("War") { viewModel.filterStatus.genre = Genre.WAR },
                            )

                            var selectedIndex5 by remember { mutableStateOf<Int?>(null) }

                            Column {
                                options5.forEachIndexed { index, option ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(vertical = 0.dp)
                                            .clickable {
                                                selectedIndex5 = index
                                                option.onSelected()
                                            }
                                    ) {
                                        Checkbox(
                                            checked = selectedIndex5 == index,
                                            onCheckedChange = {
                                                selectedIndex5 = if (it) index else null
                                                if (it) option.onSelected()
                                            }
                                        )
                                        Text(option.text, color = TextColor)
                                    }
                                }
                            }


                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onToggle, modifier = Modifier.align(Alignment.CenterHorizontally).width(200.dp)) {
                            Text("Apply")
                        }
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
