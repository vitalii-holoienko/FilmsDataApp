package com.example.filmsdataapp.presentation.components


import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmsdataapp.R
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    navigateToMainScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    navigateToSearchedTitlesScreen: () -> Unit,
    onMenuClick: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)

    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    var searchEnded = viewModel.searchEnded.observeAsState(initial = false)
    val keyboardController = LocalSoftwareKeyboardController.current

    if(searchEnded.value){
        navigateToSearchedTitlesScreen()
        viewModel.stopSearching()
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = PrimaryColor)
                .height(55.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.menu_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.CenterVertically)
                        .clickable { onMenuClick() }
                )
                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "MediaBase",
                    color = TextColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { navigateToMainScreen() },
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.boldonse_regular))
                )

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.CenterVertically)
                        .scale(1.7f)
                        .clickable {
                            isSearching = true
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.CenterVertically)
                        .scale(1.5f)
                        .clickable { navigateToProfileScreen() }
                )
            }
        }

        if (isSearching) {
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackGroundColor)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    cursorBrush = SolidColor(TextColor),
                    modifier = Modifier

                        .background(PrimaryColor, shape = RoundedCornerShape(8.dp))
                        .focusRequester(focusRequester)
                        .width(320.dp)
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    textStyle = TextStyle(
                        color = TextColor,
                        fontSize = 16.sp
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            if(searchText.length > 1) {
                                viewModel.searchTitle(searchText)
                                viewModel.searchedQuery.value = searchText
                            }
                            isSearching = false
                            searchText = ""
                        }
                    ),
                    decorationBox = { innerTextField ->
                        if (searchText.isEmpty()) {
                            Text(
                                text = "Search...",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                )
                IconButton(onClick = {
                    isSearching = false
                    searchText = ""
                }, modifier = Modifier.width(40.dp)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                    )
                }
            }
        }
        }
    }
