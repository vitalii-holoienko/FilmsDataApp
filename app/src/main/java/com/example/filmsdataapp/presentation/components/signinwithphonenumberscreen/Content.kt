package com.example.filmsdataapp.presentation.components.signinwithphonenumberscreen

import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
fun Content(navigateToMainScreen : () -> Unit, navigateToLogInScreen : () -> Unit){
    var phoneNumber by remember { mutableStateOf("") }

    var verificationCode by remember { mutableStateOf("") }

    var showWarning by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current



    val viewModel: MainActivityViewModel = viewModel(LocalContext.current as ComponentActivity)

    var appGotUserPhoneNumber = viewModel.appGotUserPhoneNumber.observeAsState()
    var userSuccessfullySignedInUsingGoogle = viewModel.userSuccessfullySignedIn.observeAsState(false)

    if(userSuccessfullySignedInUsingGoogle.value){
        navigateToMainScreen()
        viewModel.userSuccessfullySignedIn.value = false
    }



    val focusRequester = remember { FocusRequester() }

    Spacer(modifier = Modifier.height(50.dp))

    if(!appGotUserPhoneNumber.value!!){
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .align(Alignment.Start)){
                Text(
                    text = "Log in using phone number",
                    color = TextColor,
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                )
            }


            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)){
                BasicTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it},
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
                        if (phoneNumber.isEmpty()) {
                            Text(
                                text = "Phone number",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }

            Text(
                text = "Enter phone number, and we will send verification code",
                color = Color.Gray,
                fontSize = 10.sp
            )
        }
        Column(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
        ){
            Button(
                onClick = {
                    viewModel.appGotUserPhoneNumber.value = true
                    viewModel.enteredPhoneNumber.value = phoneNumber
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()

            ){
                Text(
                    text = "Send verification code",
                    modifier = Modifier.padding(8.dp, 0.dp),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }

            Box(modifier = Modifier.align(Alignment.Start)){
                Text(
                    text = "Back to Log in",
                    modifier = Modifier.clickable {
                        navigateToLogInScreen()
                    },
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = LinksColor
                )
            }
        }

    }else{
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .align(Alignment.Start)){
                Text(
                    text = "Enter verification code",
                    color = TextColor,
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                )
            }


            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)){
                BasicTextField(
                    value = verificationCode,
                    onValueChange = { verificationCode = it},
                    cursorBrush = SolidColor(TextColor),
                    modifier = Modifier

                        .background(PrimaryColor, shape = RoundedCornerShape(8.dp))
                        .focusRequester(focusRequester)
                        .onFocusChanged {}
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
                        if (verificationCode.isEmpty()) {
                            Text(
                                text = "Code",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }

            Text(
                text = "We will send verification code on your phone",
                color = Color.Gray,
                fontSize = 16.sp
            )

            Button(
                onClick = {
                    viewModel.verifyCode(verificationCode)
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()

            ){
                Text(
                    text = "Apply",
                    modifier = Modifier.padding(8.dp, 0.dp),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }
            Box(modifier = Modifier.align(Alignment.Start)){
                Text(
                    text = "Back",
                    modifier = Modifier.padding(8.dp, 0.dp).clickable {
                               viewModel.appGotUserPhoneNumber.value = false
                    },
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = LinksColor
                )
            }

        }
    }

}