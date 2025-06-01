package com.example.filmsdataapp

import android.annotation.SuppressLint
import android.content.Context
import android.credentials.GetCredentialException
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.components.NavigationMenuWrapper
import com.example.filmsdataapp.presentation.screens.AboutProgramScreen
import com.example.filmsdataapp.presentation.screens.ActorInfoScreen
import com.example.filmsdataapp.presentation.screens.ActorsScreen
import com.example.filmsdataapp.presentation.screens.AuthenticationScreen
import com.example.filmsdataapp.presentation.screens.ComingSoonScreen
import com.example.filmsdataapp.presentation.screens.CurrentlyTrendingScreen
import com.example.filmsdataapp.presentation.screens.LogInScreen
import com.example.filmsdataapp.presentation.screens.MainScreen
import com.example.filmsdataapp.presentation.screens.MoviesScreen
import com.example.filmsdataapp.presentation.screens.NewsScreen
import com.example.filmsdataapp.presentation.screens.ProfileScreen
import com.example.filmsdataapp.presentation.screens.SearchedTitlesScreen
import com.example.filmsdataapp.presentation.screens.TVShowsScreen
import com.example.filmsdataapp.presentation.screens.TitleScreen
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModel
import com.example.filmsdataapp.presentation.viewmodels.MainActivityViewModelFactory
import com.example.filmsdataapp.ui.theme.FilmsDataAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onStart() {
        super.onStart()
        viewModel.checkIfUserIsSignedIn()
    }

    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var credentialManager : CredentialManager? = CredentialManager.create(baseContext)


        viewModel = ViewModelProvider(this, MainActivityViewModelFactory(this)).get(MainActivityViewModel::class.java)
        viewModel.firebaseAuth = Firebase.auth
        viewModel.credentialManager = credentialManager

        var cp = this
        setContent {
            FilmsDataAppTheme {
                //loading initial data
                LaunchedEffect(Unit) {
                    viewModel.startInternetObserve()
                }

                var showSignInUsingGoogleOption = viewModel.showSignInUsingGoogleOption.observeAsState(false)
                    if(showSignInUsingGoogleOption.value!!){
                        val googleIdOption = GetGoogleIdOption.Builder()

                            .setServerClientId(BuildConfig.WEB_APP_CLIENT_ID)

                            .setFilterByAuthorizedAccounts(false)
                            .build()

                        val request = GetCredentialRequest.Builder()
                            .addCredentialOption(googleIdOption)
                            .build()
                        lifecycleScope.launch {
                            try {
                                // Launch Credential Manager UI
                                val result = credentialManager!!.getCredential(
                                    context = baseContext,
                                    request = request
                                )


                                // Extract credential from the result returned by Credential Manager
                                viewModel.handleSignIn(result.credential, cp)
                            } catch (e: GetCredentialException) {
                                Log.e("TEKKEN", "Couldn't retrieve user's credentials: ${e.localizedMessage}")
                            }
                        }
                        viewModel.showSignInUsingGoogleOption.value = false


                    }




                val isConnected by viewModel.isConnected.collectAsState()

                if (isConnected) {
                    LaunchedEffect(Unit){
                        viewModel.loadInitialData()
                    }
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
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
                                }
                            )
                        }
                        composable(route = "authentication_screen") {
                            AuthenticationScreen(
                                navigateToMainScreen = { navController.navigate("main_screen")},
                                navigateToProfilePage = { navController.navigate("profile_screen")},
                                onMenuClick = { scope.launch { drawerState.open() } },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
                                },
                                navigateToAuthenticationScreen= {navController.navigate("authentication_screen")},
                                navigateToLogInScreen= {navController.navigate("log_in_screen")},
                            )

                        }

                        composable(route = "log_in_screen") {
                            LogInScreen(
                                navigateToMainScreen = { navController.navigate("main_screen")},
                                navigateToProfilePage = { navController.navigate("profile_screen")},
                                onMenuClick = { scope.launch { drawerState.open() } },
                                navigateToSearchedTitleScreen = {
                                    navController.navigate("searched_titles_screen")
                                },
                                navigateToAuthenticationScreen= {navController.navigate("authentication_screen")})

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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
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
                                },
                                 navigateToAuthenticationScreen = {
                                     navController.navigate("authentication_screen")
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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
                                }

                            )
                        }

                        composable(route = "actor_info_screen") {
                            ActorInfoScreen(
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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
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
                                },
                                navigateToActorInfoScreen = {
                                    navController.navigate("actor_info_screen")
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
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
                                },
                                navigateToAuthenticationScreen = {
                                    navController.navigate("authentication_screen")
                                }
                            )

                        }
                    }
                }


            }
        }
    }
}


