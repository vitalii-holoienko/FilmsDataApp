package com.example.filmsdataapp.presentation.components.createprofilescreen

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun Content(){
    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)
    var nickname by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var userImage by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            userImage = it
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    Spacer(modifier = Modifier.height(50.dp))
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 10.dp)){
            Box(modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(color = Color.Black), contentAlignment = Alignment.Center){
                AsyncImage(
                    model = userImage ?: R.drawable.add_profile_image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape).clickable {
                            launcher.launch("image/*")
                        },
                    contentScale = ContentScale.Crop
                )

                }

            Spacer(modifier = Modifier.height(30.dp))
            BasicTextField(
                value = nickname,
                onValueChange = { nickname = it},
                cursorBrush = SolidColor(TextColor),
                modifier = Modifier

                    .background(PrimaryColor, shape = RoundedCornerShape(8.dp))
                    .focusRequester(focusRequester)
                    .onFocusChanged { }
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
                    if (nickname.isEmpty()) {
                        Text(
                            text = "Nickname",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            BasicTextField(
                value = description,
                onValueChange = { description = it},
                cursorBrush = SolidColor(TextColor),
                modifier = Modifier

                    .background(PrimaryColor, shape = RoundedCornerShape(8.dp))
                    .focusRequester(focusRequester)
                    .onFocusChanged { }
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
                    if (description.isEmpty()) {
                        Text(
                            text = "Description",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                  viewModel.createUserAccount(nickname, description, userImage)
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Create profile",
                    modifier = Modifier.padding(8.dp, 0.dp),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }


        }
    }
}