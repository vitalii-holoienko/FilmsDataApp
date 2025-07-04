package com.example.filmsdataapp.presentation.components.titlescreen

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import kotlinx.coroutines.launch

@Composable
fun ExpandableMenu(
    title: String,
    menuItems: List<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    inWhichListUserHasThisTitle : String,
    titleId:String
) {
    var expanded by remember { mutableStateOf(false) }
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)

    var finalListWhereUserHasTitle by remember {
        mutableStateOf(inWhichListUserHasThisTitle)
    }

    LaunchedEffect(inWhichListUserHasThisTitle) {
        finalListWhereUserHasTitle = inWhichListUserHasThisTitle
    }
    Column(modifier = modifier) {

        Row(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth()
                .background(Color(218, 241, 255))
                .clickable { expanded = !expanded }
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .background(Color(188, 230, 255))
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color(23, 96, 161),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color(157, 162, 168)
            )
        }


        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(1.dp, Color.LightGray)
            ) {
                val currentListKey = finalListWhereUserHasTitle.lowercase().replace(" ", "")
                menuItems.forEach { option ->
                    val optionKey = option.lowercase().replace(" ", "")

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemSelected(option)
                                expanded = false
                                finalListWhereUserHasTitle = option
                            }
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )

                        if (optionKey == currentListKey) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = Color(0xFF4CAF50),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Divider()
                }
                val coroutineScope = rememberCoroutineScope()
                if (finalListWhereUserHasTitle != "no") {
                    Text(
                        text = "Delete for the list",
                        fontSize = 14.sp,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                finalListWhereUserHasTitle = "no"
                                expanded = false
                                coroutineScope.launch {
                                    viewModel.deleteTitleForAllLists(titleId)

                                }
                            }
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                    )
                }

            }
        }
    }
}