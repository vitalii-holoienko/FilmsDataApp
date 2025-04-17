package com.example.filmsdataapp.presentation.components.listofmoviesscreen

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

    // Анимированное смещение в пикселях
    val offsetX by animateFloatAsState(
        targetValue = if (isFilterVisible) 0f else with(density) { filterWidth.toPx() },
        animationSpec = tween(durationMillis = 300),
        label = "offsetX"
    )

    // Обёртка для Box, чтобы использовать align
    Box(modifier = Modifier.fillMaxWidth().height(h)) {
        // Панель и кнопка (выезжают вместе)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .width(filterWidth + buttonWidth) // панель + место для кнопки
                .align(Alignment.TopEnd)
        ) {
            // Панель фильтров
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(filterWidth)
                    .background(Color.White)
                    .shadow(8.dp)
                    .align(Alignment.CenterEnd) // панель прижата к правой стороне
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Фильтры", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange = {})
                        Text("Комедия")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onToggle) {
                        Text("Применить")
                    }
                }
            }

            // Кнопка слева от панели
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart) // кнопка прижата к левой стороне
                    .width(buttonWidth)
                    .height(80.dp)
                    .offset {IntOffset(0,80) }
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                    .background(PrimaryColor)
                    .clickable { onToggle() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_arrow_2_left),
                    contentDescription = "Открыть фильтр",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}