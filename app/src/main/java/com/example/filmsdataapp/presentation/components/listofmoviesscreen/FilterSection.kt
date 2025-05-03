package com.example.filmsdataapp.presentation.components.listofmoviesscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.filmsdataapp.domain.model.FilterOption
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun FilterSection(title: String, options: List<FilterOption>) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    Text(title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(12.dp), color = TextColor)

    Column {
        options.forEachIndexed { index, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        selectedIndex = index
                        option.onSelected()
                    }
            ) {
                Checkbox(
                    checked = selectedIndex == index,
                    onCheckedChange = {
                        if (it) {
                            selectedIndex = index
                            option.onSelected()
                        } else {
                            selectedIndex = null
                            option.onUnSelected()
                        }
                    }
                )
                Text(option.text, color = TextColor)
            }
        }
    }
}