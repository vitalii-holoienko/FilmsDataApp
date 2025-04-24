package com.example.filmsdataapp.presentation.components.listofmoviesscreen

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.filmsdataapp.R
import com.example.filmsdataapp.domain.model.FilterOption
import com.example.filmsdataapp.ui.theme.PrimaryColor
import kotlin.math.roundToInt

@Composable
fun FilterPanelWithButton(
    isFilterVisible: Boolean,
    onToggle: () -> Unit,
    h: Dp
) {
    val filterWidth = 300.dp
    val buttonWidth = 40.dp
    val density = LocalDensity.current

    val offsetX by animateFloatAsState(
        targetValue = if (isFilterVisible) 0f else with(density) { filterWidth.toPx() },
        animationSpec = tween(durationMillis = 300),
        label = "offsetX"
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(h)) {
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
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Filters", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Column {
                        val options = listOf("Option 1", "Option 2", "Option 3")
                        var selectedIndex by remember { mutableStateOf<Int?>(null) }

                        Column {
                            val options = listOf(
                                FilterOption("Option 1") { Log.d("CHECKBOX", "Option 1 selected") },
                                FilterOption("Option 2") { Log.d("CHECKBOX", "Option 2 selected") },
                                FilterOption("Option 3") { Log.d("CHECKBOX", "Option 3 selected") }
                            )

                            var selectedIndex by remember { mutableStateOf<Int?>(null) }

                            Column {
                                options.forEachIndexed { index, option ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(vertical = 4.dp)
                                            .clickable {
                                                selectedIndex = index
                                                option.onSelected()
                                            }
                                    ) {
                                        Checkbox(
                                            checked = selectedIndex == index,
                                            onCheckedChange = {
                                                selectedIndex = if (it) index else null
                                                if (it) option.onSelected()
                                            }
                                        )
                                        Text(option.text)
                                    }
                                }
                            }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onToggle) {
                        Text("Применить")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .width(buttonWidth)
                    .height(80.dp)
                    .offset { IntOffset(0, 80) }
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
}
