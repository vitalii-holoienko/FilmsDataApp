package com.example.filmsdataapp.presentation.components.profilescreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.filmsdataapp.R
import com.example.filmsdataapp.ui.theme.LinksColor
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
fun Content(){
    Spacer(modifier = Modifier.height(30.dp))
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
        Column(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
                verticalAlignment = Alignment.Top
            ){
                Image(
                    painter = painterResource(id = R.drawable.test_image),
                    contentDescription = "",
                    Modifier
                        .size(95.dp)
                        .scale(1f),
                    contentScale = ContentScale.Crop

                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    verticalArrangement = Arrangement.Top
                ){
                    Text(
                        text = "Mora64",
                        color = TextColor,
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                        modifier = Modifier.padding(0.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "18 y.o / using app since Nov. 2025",
                        color = TextColor,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)){
                Text(
                    text = "History",
                    color = LinksColor,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Settings",
                    color = LinksColor,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                    modifier = Modifier.padding(0.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)){
                Text(
                    text = "List of movies",
                    color = TextColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Spacer(modifier = Modifier
                    .height(15.dp)
                    .background(Color(170, 170, 170))
                    .fillMaxWidth())

                Spacer(modifier = Modifier.height(5.dp))
                ClickableTextExample()

            }
            Spacer(modifier = Modifier.height(20.dp))
            TimeProgressBar(0.5f)
            Spacer(modifier = Modifier.height(40.dp))


            Text(
                text = "Activity",
                color = TextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                modifier = Modifier.padding(0.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))

            //TODO Activity graph
            Spacer(modifier= Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(Color.Black))



        }
    }
}

@Composable
fun TimeProgressBar(
    currentProgress: Float
) {
    val milestones = listOf("1 week", "1 month", "3 months", "6 months", "1 year")
    val milestoneCount = milestones.size
    val reachedMilestones = (currentProgress * milestoneCount).toInt()

    Column() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

            Text("Time spent watching movies", fontWeight = FontWeight.Bold, color = TextColor, fontSize = 13.sp)
            Text("1 month & 3 weeks", fontWeight = FontWeight.Bold, color = TextColor, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val barHeight = 6.dp.toPx()
                val y = size.height / 2

                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = barHeight
                )

                drawLine(
                    color = Color(0xFFFF6B6B),
                    start = Offset(0f, y),
                    end = Offset(size.width * currentProgress, y),
                    strokeWidth = barHeight
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            milestones.forEachIndexed { index, label ->
                Text(
                    text = label,
                    color = if (index < reachedMilestones) Color(0xFF2ECC71) else Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun ClickableTextExample() {
    val annotatedText = buildAnnotatedString {


        pushStringAnnotation(tag = "PLANNED", annotation = "planned_clicked")
        withStyle(style = SpanStyle(color = LinksColor)) {
            append("Planned (1)")
        }
        pop()

        append(" / ")

        pushStringAnnotation(tag = "WATCHING", annotation = "watching_clicked")
        withStyle(style = SpanStyle(color = LinksColor)) {
            append("Watching (2)")
        }
        pop()
        append(" / ")

        pushStringAnnotation(tag = "COMPLETED", annotation = "completed_clicked")
        withStyle(style = SpanStyle(color = LinksColor)) {
            append("Completed (2)")
        }
        pop()

        append(" / \n")

        pushStringAnnotation(tag = "ON_HOLD", annotation = "on_hold_clicked")
        withStyle(style = SpanStyle(color = LinksColor)) {
            append("On Hold (1)")
        }
        pop()

        append(" / ")

        pushStringAnnotation(tag = "DROPPED", annotation = "dropped_clicked")
        withStyle(style = SpanStyle(color = LinksColor)) {
            append("Dropped (4)")
        }
    }

    ClickableText(
        text = annotatedText,
        style = TextStyle(
            fontSize = 13.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght))
        ),
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "PLANNED", start = offset, end = offset)
                .firstOrNull()?.let {
                    println("Planned clicked")
                }

            annotatedText.getStringAnnotations(tag = "WATCHING", start = offset, end = offset)
                .firstOrNull()?.let {
                    println("Watching clicked")
                }

            annotatedText.getStringAnnotations(tag = "COMPLETED", start = offset, end = offset)
                .firstOrNull()?.let {
                    println("Completed clicked")
                }

            annotatedText.getStringAnnotations(tag = "ON_HOLD", start = offset, end = offset)
                .firstOrNull()?.let {
                    println("On Hold clicked")
                }
            annotatedText.getStringAnnotations(tag = "DROPPED", start = offset, end = offset)
                .firstOrNull()?.let {
                    println("On Hold clicked")
                }
        }
    )
}