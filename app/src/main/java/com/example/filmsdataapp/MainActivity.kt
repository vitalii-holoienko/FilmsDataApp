package com.example.filmsdataapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filmsdataapp.presentation.components.NavigationMenuWrapper
import com.example.filmsdataapp.presentation.screens.AboutProgramScreen
import com.example.filmsdataapp.presentation.screens.ActorsScreen
import com.example.filmsdataapp.presentation.screens.ContactsScreen
import com.example.filmsdataapp.presentation.screens.CurrentlyTrendingScreen
import com.example.filmsdataapp.presentation.screens.MainScreen
import com.example.filmsdataapp.presentation.screens.MoviesScreen
import com.example.filmsdataapp.presentation.screens.NewReleasesScreen
import com.example.filmsdataapp.presentation.screens.ProfileScreen
import com.example.filmsdataapp.presentation.screens.TVShowsScreen
import com.example.filmsdataapp.ui.theme.FilmsDataAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmsDataAppTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                NavigationMenuWrapper(
                    drawerState = drawerState,
                    scope = scope,
                    navController
                ) {
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
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }

                            )
                        }
                        composable(route = "movies_screen") {
                            MoviesScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }

                            )
                        }
                        composable(route = "actors_screen") {
                            ActorsScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }

                            )
                        }

                        composable(route = "tvshows_screen") {
                            TVShowsScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }

                            )
                        }

                        composable(route = "new_releases_screen") {
                            NewReleasesScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                        composable(route = "about_program_screen") {
                            AboutProgramScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                        composable(route = "reviews_screen") {
                            AboutProgramScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                        composable(route = "contacts_screen") {
                            ContactsScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }

                        composable(route = "currently_trending_screen") {
                            CurrentlyTrendingScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                        composable(route = "profile_screen"){
                            ProfileScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )

                        }
                    }
                }


            }
        }
    }
}


