package com.example.filmsdataapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filmsdataapp.presentation.screens.CurrentlyTrendingScreen
import com.example.filmsdataapp.presentation.screens.MainScreen
import com.example.filmsdataapp.presentation.screens.NewReleasesScreen
import com.example.filmsdataapp.ui.theme.FilmsDataAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmsDataAppTheme {
                val navController = rememberNavController()
                AnimatedNavHost(navController = navController, //using AnimatedNavHost to remove fade animation
                    startDestination = "main_screen",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }){
                    composable(route = "main_screen") {
                        MainScreen(
                            navigateToNewReleasesPage = {
                                    navController.navigate("new_releases_screen")
                            },
                            navigateToCurrentlyTrendingPage = {
                                navController.navigate("currently_trending_screen")
                            }
                        )
                    }
                    composable(route = "new_releases_screen") {
                        NewReleasesScreen(
                            navigateToMainScreen = {
                                navController.navigate("main_screen")
                            }
                        )
                    }
                    composable(route = "currently_trending_screen") {
                        CurrentlyTrendingScreen(
                            navigateToMainScreen = {
                                navController.navigate("main_screen")
                            }
                        )
                    }
                }

            }
        }
    }
}


