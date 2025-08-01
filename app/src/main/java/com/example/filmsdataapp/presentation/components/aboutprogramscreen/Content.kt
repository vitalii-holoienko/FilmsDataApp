package com.example.filmsdataapp.presentation.components.aboutprogramscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmsdataapp.R
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.TextColor

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
        modifier = Modifier.padding(bottom = 8.dp, top = 16.dp),
        color = TextColor
    )
}

@Composable
private fun SectionText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontFamily = FontFamily(Font(R.font.notosans_variablefont_wdth_wght)),
        modifier = Modifier.padding(bottom = 12.dp),
        lineHeight = 20.sp,
        color = TextColor
    )
}
@Composable
fun Content(viewModel : MainActivityViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "About MediaBase",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp),
            color = TextColor
        )

        Text(
            text = "FilmsDataApp is a modern Android application designed for movie lovers who want to stay up-to-date with the latest releases and timeless classics. This app provides a convenient and beautiful way to browse, discover, and get detailed information about movies and TV shows.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 24.dp),
            color = TextColor
        )

        SectionTitle("Key Features")
        SectionText(
            """
            • Up-to-date movie catalogs — browse popular, new, and recommended movies updated regularly.
            • Detailed movie info — descriptions, genres, release year, ratings, and more for each title.
            • Intuitive user interface — built with Jetpack Compose, Google’s modern UI toolkit for Android, delivering smooth animations and seamless navigation.
            • Read reviews — users can read detailed reviews for each movie.
            • Save and rate movies — users can save favorite movies and rate them.
            """.trimIndent()
        )

        SectionTitle("Technologies and Architecture")
        SectionText(
            """
            This project follows the best Android development practices:
            
            • Kotlin — a modern and powerful programming language for Android.
            • Jetpack Compose — a declarative UI framework simplifying UI development.
            • MVVM (Model-View-ViewModel) — an architecture pattern providing clear separation of concerns and easier testing.
            • OkHttp — efficient HTTP client used for network requests.
            """.trimIndent()
        )

        SectionTitle("Goals and Future Plans")
        SectionText(
            """
            This project was created primarily for learning and showcasing modern Android development skills and architectural patterns. Future enhancements may include:
            • Supporting offline data caching.
            • Introducing more complex navigation and animations.
            • Improving support for different screen sizes and devices.
            """.trimIndent()
        )

        SectionTitle("Thankfulness")
        SectionText(
            "Thanks to everyone interested in development and supporting the creation of useful apps! FilmsDataApp is a step towards making the world of cinema closer and more accessible to everyone."
        )
    }
}