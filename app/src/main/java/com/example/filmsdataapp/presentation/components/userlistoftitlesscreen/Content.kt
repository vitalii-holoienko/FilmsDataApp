package com.example.filmsdataapp.presentation.components.userlistoftitlesscreen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmsdataapp.R
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.LinksColor
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(list:String){
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)
    var nickname by remember { mutableStateOf("-") }
    LaunchedEffect(Unit){
        viewModel.getUserNickname {
            nickname = it
        }
    }
    Column(modifier = Modifier
        .padding(10.dp)
        .verticalScroll(rememberScrollState())) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable {
                viewModel.onProfileClicked()
            }, verticalArrangement = Arrangement.Center){
                Image(
                    painter = painterResource(id = R.drawable.go_back_arrow_icon),
                    contentDescription = "",
                    Modifier
                        .size(25.dp)
                        .scale(1.5f)
                        .padding(5.dp, 0.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                )
                Text(
                    text = "Go back",
                    color = LinksColor,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                )
            }


            Text(
                text = nickname,
                color = TextColor,
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(5.dp, 0.dp).align(Alignment.CenterVertically)
            )

        }
        
        Spacer(modifier = Modifier.height(15.dp))

        var filterText by remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusRequester = remember { FocusRequester() }
        BasicTextField(
            value = filterText,
            onValueChange = { filterText = it },
            cursorBrush = SolidColor(TextColor),
            modifier = Modifier
                .background(PrimaryColor)
                .focusRequester(focusRequester)
                .fillMaxWidth()
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
                }
            ),
            decorationBox = { innerTextField ->
                if (filterText.isEmpty()) {
                    Text(
                        text = "Search title",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(color = PrimaryColor)
            .clickable { viewModel.onCurrentlyTrendingTitlesClicked() }
        ){
            Text(
                text = list.replaceFirstChar{it.uppercaseChar()},
                color = TextColor,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(5.dp, 0.dp)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))

        val titles by viewModel.listOfTitlesToDisplay

        LaunchedEffect(Unit) {
            viewModel.getListOfTitlesByName(list)
        }


        Column {

            val filteredTitles = titles.filter {
                it.primaryTitle!!.startsWith(filterText, ignoreCase = true)
            }

            filteredTitles.forEachIndexed { index, title ->
                Row(modifier = Modifier.padding(5.dp)) {
                    Text(text = "${index + 1}", color = TextColor, fontSize = 15.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "${title.primaryTitle}", modifier = Modifier.clickable {
                        viewModel.onTitleClicked(title)
                    }, color = LinksColor, fontSize = 15.sp)
                }
            }
        }
    }
}