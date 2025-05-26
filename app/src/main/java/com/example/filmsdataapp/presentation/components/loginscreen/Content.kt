package com.example.filmsdataapp.presentation.components.loginscreen

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmsdataapp.R
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.LinksColor
import com.example.filmsdataapp.ui.theme.PrimaryColor
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun Content(navigateToMainScreen : () -> Unit, navigateToAuthenticationScreen : () -> Unit){
    var loginText by remember { mutableStateOf("") }

    var passwordText by remember { mutableStateOf("") }

    var showWarning by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)

    val focusRequester = remember { FocusRequester() }
    Spacer(modifier = Modifier.height(50.dp))
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Box(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .align(Alignment.Start)){
            Text(
                text = "Join",
                color = TextColor,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
            )
        }

        Box(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(170.dp)
            .background(color = PrimaryColor))
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)){
            BasicTextField(
                value = loginText,
                onValueChange = { loginText = it;  },
                cursorBrush = SolidColor(TextColor),
                modifier = Modifier

                    .background(PrimaryColor, shape = RoundedCornerShape(8.dp))
                    .focusRequester(focusRequester).onFocusChanged { showWarning = false }
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
                    if (loginText.isEmpty()) {
                        Text(
                            text = "Email",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
        }


        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)){
            BasicTextField(
                value = passwordText,
                onValueChange = { passwordText = it; showWarning = true;},
                cursorBrush = SolidColor(TextColor),
                modifier = Modifier
                    .background(PrimaryColor, shape = RoundedCornerShape(8.dp))
                    .focusRequester(focusRequester).onFocusChanged { showWarning = false; }
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
                    if (passwordText.isEmpty()) {
                        Text(
                            text = "Password",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
        }
        if(showWarning){
            Box(modifier = Modifier.align(Alignment.Start).padding(horizontal = 16.dp, vertical = 0.dp)){
                Text(
                    text = "Something went wrong",
                    color = Color(android.graphics.Color.rgb(249, 55, 57)),
                    fontSize = 16.sp,
                )
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .height(100.dp)){
            val c = LocalContext.current
            Button(
                onClick = {
                    val auth = viewModel.firebaseAuth
                    auth.signInWithEmailAndPassword(loginText, passwordText)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                viewModel.user = auth.currentUser
                                viewModel.userNotSingedIn.value = false
                                navigateToMainScreen()

                            } else {
                                showWarning = true;
                            }
                        }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Continue with email",
                    modifier = Modifier.padding(8.dp, 0.dp),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }

            Row(){
                Text(
                    text = "You have not account?",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Sing up",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = LinksColor,
                    modifier = Modifier.clickable { navigateToAuthenticationScreen() }
                )
            }
        }

    }
}