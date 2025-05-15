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
fun FilterSection(
    title: String,
    options: List<FilterOption>,
    singleSelection: Boolean
) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var selectedIndices by remember { mutableStateOf(setOf<Int>()) }

    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(12.dp),
        color = TextColor
    )

    Column {
        options.forEachIndexed { index, option ->
            val isChecked = if (singleSelection) {
                selectedIndex == index
            } else {
                selectedIndices.contains(index)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        if (singleSelection) {
                            selectedIndex = index
                            option.onSelected()
                        } else {
                            val newSet = if (selectedIndices.contains(index)) {
                                selectedIndices - index
                            } else {
                                selectedIndices + index
                            }
                            selectedIndices = newSet

                            if (index in selectedIndices) {
                                option.onSelected()
                            } else {
                                option.onUnSelected()
                            }
                        }
                    }
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        if (singleSelection) {
                            selectedIndex = if (checked) {
                                option.onSelected()
                                index
                            } else {
                                option.onUnSelected()
                                null
                            }
                        } else {
                            val newSet = if (checked) {
                                option.onSelected()
                                selectedIndices + index
                            } else {
                                option.onUnSelected()
                                selectedIndices - index
                            }
                            selectedIndices = newSet
                        }
                    }
                )
                Text(option.text, color = TextColor)
            }
        }
    }
}