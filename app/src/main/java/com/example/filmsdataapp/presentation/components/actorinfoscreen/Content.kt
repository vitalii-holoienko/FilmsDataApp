package com.example.filmsdataapp.presentation.components.actorinfoscreen

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.filmsdataapp.R
import com.example.filmsdataapp.presentation.utils.NumsFormatter
import com.example.filmsdataapp.presentation.utils.TimeFormatter
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.BackGroundColor
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun Content(viewModel : MainActivityViewModel){

    var info = viewModel.displayedActorInfo.observeAsState()
    Log.d("TEKKEN", info.value!!.primaryImage.toString())
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackGroundColor)
        .verticalScroll(rememberScrollState())
        .padding(10.dp)) {
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = info.value!!.nameText.text,
            color = TextColor,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
            modifier = Modifier.padding(0.dp, 8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.width(IntrinsicSize.Max)) {
            Image(
                painter = rememberAsyncImagePainter(info.value!!.primaryImage!!.url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .width(230.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(30.dp))


        }
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(android.graphics.Color.rgb(49, 50, 50)))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(8.dp)
                        .background(color = Color(android.graphics.Color.rgb(42, 44, 43)))
                )
                Text(
                    text = "Biography",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .weight(1f),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = info.value!!.bio!!.text!!.plainText,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                color = TextColor
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(android.graphics.Color.rgb(49, 50, 50)))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(8.dp)
                        .background(color = Color(android.graphics.Color.rgb(42, 44, 43)))
                )
                Text(
                    text = "Information",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .weight(1f),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                    color = TextColor
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Spacer(modifier = Modifier.height(15.dp))
        Column{
            if(info.value!!.birthDate != null){
                if(info.value!!.birthDate!!.date != null){
                    Text(
                        text = "Birthdate: ${info.value!!.birthDate!!.date.toString().replace("-", ".")}",
                        color = Color(android.graphics.Color.rgb(232, 235, 239)),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                        modifier = Modifier.padding(0.dp,4.dp)
                    )
                }
            }


            if(info.value!!.birthLocation != null){
                if(info.value!!.birthLocation!!.text != null){
                    Text(
                        text = "Place of birth: ${info.value!!.birthLocation!!.text.toString()}",
                        color = Color(android.graphics.Color.rgb(232, 235, 239)),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                        modifier = Modifier.padding(0.dp,4.dp)
                    )
                }
            }



            if(info.value!!.spouses!=null){
                info.value!!.spouses!!.forEach {
                    if(it.current!!){
                        if(it.spouse!!.name !== null ){
                            Text(
                                text = "Spouse: ${it.spouse!!.name!!.nameText}",
                                color = Color(android.graphics.Color.rgb(232, 235, 239)),
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                                modifier = Modifier.padding(0.dp,4.dp)
                            )
                        }

                    }
                }
            }
            if(info.value!!.birthName != null){
                if(info.value!!.birthName!!.text != null){

                    Text(
                        text = "Birth name: ${info.value!!.birthName!!.text}",
                        color = Color(android.graphics.Color.rgb(232, 235, 239)),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                        modifier = Modifier.padding(0.dp,4.dp)
                    )
                }
            }
            if(info.value!!.height != null){
                if(info.value!!.height!!.measurement != null){
                    if(info.value!!.height!!.measurement.value != null){
                        Text(
                            text = "Height: ${info.value!!.height!!.measurement.value.toInt()}",
                            color = Color(android.graphics.Color.rgb(232, 235, 239)),
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                            modifier = Modifier.padding(0.dp,4.dp)
                        )
                    }
                }
            }




            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .background(color = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(android.graphics.Color.rgb(49, 50, 50)))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(8.dp)
                            .background(color = Color(android.graphics.Color.rgb(42, 44, 43)))
                    )
                    Text(
                        text = "External links",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            .weight(1f),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
                        color = TextColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            if(info.value!!.officialLinks !== null){
                if(info.value!!.officialLinks!!.edges != null){
                    Column{
                        info.value!!.officialLinks!!.edges!!.forEach {
                            Text(
                                text = "${it.node!!.label}: ${it.node.url}",
                                color = Color(android.graphics.Color.rgb(232, 235, 239)),
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                                modifier = Modifier.padding(0.dp,4.dp)
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }

                }
            }



        }
    }

}