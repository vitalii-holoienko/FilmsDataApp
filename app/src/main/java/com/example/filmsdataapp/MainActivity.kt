package com.example.filmsdataapp

import android.net.Uri
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.components.NavigationMenuWrapper
import com.example.filmsdataapp.presentation.screens.AboutProgramScreen
import com.example.filmsdataapp.presentation.screens.ActorsScreen
import com.example.filmsdataapp.presentation.screens.ComingSoonScreen
import com.example.filmsdataapp.presentation.screens.ContactsScreen
import com.example.filmsdataapp.presentation.screens.CurrentlyTrendingScreen
import com.example.filmsdataapp.presentation.screens.MainScreen
import com.example.filmsdataapp.presentation.screens.MoviesScreen
import com.example.filmsdataapp.presentation.screens.NewsScreen
import com.example.filmsdataapp.presentation.screens.ProfileScreen
import com.example.filmsdataapp.presentation.screens.SearchedTitlesScreen
import com.example.filmsdataapp.presentation.screens.TVShowsScreen
import com.example.filmsdataapp.presentation.screens.TitleScreen
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.ui.theme.FilmsDataAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmsDataAppTheme {
                val viewModel : MainActivityViewModel = viewModel()

                //loading initial data
                LaunchedEffect(Unit) {
                    viewModel.loadInitialData()
                }


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
                                },
                                navigateToTVShowsScreen = {
                                    navController.navigate("tvshows_screen")
                                },
                                navigateToMoviesScreen = {
                                    navController.navigate("movies_screen")
                                },
                                navigateToActorsScreen = {
                                    navController.navigate("actors_screen")
                                },
                                navigateToTitleScreen = { title ->
                                    val json = Json.encodeToString(title)
                                    val encoded = Uri.encode(json)
                                    navController.navigate("title_screen/$encoded")
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
                                },
                                navigateToNewsScreen = { news ->
                                    val json = Json.encodeToString(news)
                                    val encoded = Uri.encode(json)
                                    navController.navigate("news_screen/$encoded")
                                },


                            )
                        }


                        composable(
                            route = "title_screen/{titleJson}",
                            arguments = listOf(navArgument("titleJson") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val json = backStackEntry.arguments?.getString("titleJson")
                            val title = json?.let { Json.decodeFromString<Title>(it) }

                            TitleScreen(
                                title = title!!,
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
                                }
                            )
                        }

                        composable(
                            route = "news_screen/{newsJson}",
                            arguments = listOf(navArgument("newsJson") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val json = backStackEntry.arguments?.getString("newsJson")
                            val news = json?.let { Json.decodeFromString<News>(it) }

                             NewsScreen(
                                news = news!!,
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
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
                                },
                                navigateToTitleScreen = { title ->
                                    val json = Json.encodeToString(title)
                                    val encoded = Uri.encode(json)
                                    navController.navigate("title_screen/$encoded")
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
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
                                },
                                navigateToTitleScreen = { title ->
                                    val json = Json.encodeToString(title)
                                    val encoded = Uri.encode(json)
                                    navController.navigate("title_screen/$encoded")
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
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
                                },
                                navigateToTitleScreen = { title ->
                                    val json = Json.encodeToString(title)
                                    val encoded = Uri.encode(json)
                                    navController.navigate("title_screen/$encoded")
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
                                }

                            )
                        }

                        composable(route = "new_releases_screen") {
                            ComingSoonScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                },
                                navigateToTitleScreen = { title ->
                                    val json = Json.encodeToString(title)
                                    val encoded = Uri.encode(json)
                                    navController.navigate("title_screen/$encoded")
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
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
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
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
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
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
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
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
                                },
                                navigateToTitleScreen = { title ->
                                    val json = Json.encodeToString(title)
                                    val encoded = Uri.encode(json)
                                    navController.navigate("title_screen/$encoded")
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
                                }
                            )
                        }

                        composable(route = "searched_titles_screen") {
                            SearchedTitlesScreen(
                                navigateToMainScreen = {
                                    navController.navigate("main_screen")
                                },
                                navigateToProfilePage = {
                                    navController.navigate("profile_screen")
                                },
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                },
                                navigateToTitleScreen = { title ->
                                    val json = Json.encodeToString(title)
                                    val encoded = Uri.encode(json)
                                    navController.navigate("title_screen/$encoded")
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
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
                                },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
                                }
                            )

                        }
                    }
                }


            }
        }
    }
}


