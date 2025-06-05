package com.example.filmsdataapp

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.presentation.common.NavigationEvent
import com.example.filmsdataapp.presentation.components.NavigationMenuWrapper
import com.example.filmsdataapp.presentation.screens.AboutProgramScreen
import com.example.filmsdataapp.presentation.screens.ActorInfoScreen
import com.example.filmsdataapp.presentation.screens.ActorsScreen
import com.example.filmsdataapp.presentation.screens.AuthenticationScreen
import com.example.filmsdataapp.presentation.screens.ComingSoonScreen
import com.example.filmsdataapp.presentation.screens.CreateProfileScreen
import com.example.filmsdataapp.presentation.screens.CurrentlyTrendingScreen
import com.example.filmsdataapp.presentation.screens.LogInScreen
import com.example.filmsdataapp.presentation.screens.MainScreen
import com.example.filmsdataapp.presentation.screens.MoviesScreen
import com.example.filmsdataapp.presentation.screens.NewsScreen
import com.example.filmsdataapp.presentation.screens.ProfileScreen
import com.example.filmsdataapp.presentation.screens.SearchedTitlesScreen
import com.example.filmsdataapp.presentation.screens.SignInWithPhoneNumberScreen
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
import java.net.URLEncoder
import kotlin.math.sign

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
                if (showSignInUsingGoogleOption.value) {
                    viewModel.showSignInUsingGoogleOption.value = false

                    val googleIdOption = GetGoogleIdOption.Builder()
                        .setServerClientId(BuildConfig.WEB_APP_CLIENT_ID)
                        .setFilterByAuthorizedAccounts(false)
                        .build()

                    val request = GetCredentialRequest.Builder()
                        .addCredentialOption(googleIdOption)
                        .build()

                    lifecycleScope.launch {
                        try {
                            val result = credentialManager!!.getCredential(
                                context = baseContext,
                                request = request
                            )

                            viewModel.handleSignIn(result.credential, cp)

                        } catch (e: GetCredentialCancellationException) {

                            Log.i("TEKKEN", "User cancelled credential selection")
                        } catch (e: GetCredentialException) {

                            Log.e("TEKKEN", "CredentialManager error: ${e.localizedMessage}")
                        } catch (e: Exception) {

                            Log.e("TEKKEN", "Unknown error: ${e.localizedMessage}")
                        }
                    }
                }




                val isConnected by viewModel.isConnected.collectAsState()
                var appGotUserPhoneNumber = viewModel.appGotUserPhoneNumber.observeAsState()
                val navigationEvent by viewModel.navigation.collectAsState()
                if (isConnected) {
                    LaunchedEffect(Unit){
                        viewModel.loadInitialData()
                    }
                }
                val activity = LocalContext.current as Activity
                if(appGotUserPhoneNumber.value!!){
                    LaunchedEffect(Unit){
                        viewModel.startPhoneNumberVerification(activity)
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
                            MainScreen()
                        }
                        composable(route = "authentication_screen") {
                            AuthenticationScreen()

                        }

                        composable(route = "log_in_screen") {
                            LogInScreen()
                        }

                        composable(route="create_profile_screen"){
                            CreateProfileScreen()

                        }

                        composable(route = "sign_in_with_phone_number_screen"){
                            SignInWithPhoneNumberScreen()
                        }




                        composable(
                            route = "title_screen/{titleJson}",
                            arguments = listOf(navArgument("titleJson") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val json = backStackEntry.arguments?.getString("titleJson")
                            val title = json?.let { Json.decodeFromString<Title>(it) }

                            TitleScreen(title = title!!,)
                        }

                        composable(
                            route = "news_screen/{newsJson}",
                            arguments = listOf(navArgument("newsJson") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val json = backStackEntry.arguments?.getString("newsJson")
                            val news = json?.let { Json.decodeFromString<News>(it) }

                             NewsScreen(news = news!!,)
                        }


                        composable(route = "movies_screen") {
                            MoviesScreen()
                        }

                        composable(route = "actor_info_screen") {
                            ActorInfoScreen()
                        }
                        composable(route = "actors_screen") {
                            ActorsScreen()
                        }

                        composable(route = "tvshows_screen") {
                            TVShowsScreen()
                        }

                        composable(route = "new_releases_screen") {
                            ComingSoonScreen()
                        }
                        composable(route = "about_program_screen") {
                            AboutProgramScreen()
                        }


                        composable(route = "currently_trending_screen") {
                            CurrentlyTrendingScreen()
                        }

                        composable(route = "searched_titles_screen") {
                            SearchedTitlesScreen()
                        }
                        composable(route = "profile_screen"){
                            ProfileScreen()
                        }
                    }
                }

                LaunchedEffect(navigationEvent) {
                    navigationEvent?.let { event ->
                        when (event) {
                            is NavigationEvent.ToNews -> {
                                val json = Json.encodeToString(event.news)
                                val encoded = Uri.encode(json)

                                navController.navigate("news_screen/$encoded")

                                viewModel.clearNavigation()
                            }
                            else -> {}
                        }
                    }
                }



                LaunchedEffect(navigationEvent) {
                    navigationEvent?.let { event ->
                        when (event) {
                            is NavigationEvent.ToTitle -> {
                                val json = Json.encodeToString(event.title)
                                val encoded = Uri.encode(json)
                                navController.navigate("title_screen/$encoded")

                                viewModel.clearNavigation()
                            }
                            else -> {}
                        }
                    }
                }




                LaunchedEffect(navigationEvent) {
                    when (navigationEvent) {
                        is NavigationEvent.ToProfile -> {
                            navController.navigate("profile_screen")
                        }
                        is NavigationEvent.ToMain -> {
                            navController.navigate("main_screen")
                        }
                        is NavigationEvent.ToAuth -> {
                            navController.navigate("authentication_screen")
                        }
                        is NavigationEvent.OpenNav -> {
                            scope.launch { drawerState.open() }
                        }
                        is NavigationEvent.ToSearchTitle -> {
                            navController.navigate("searched_titles_screen")
                        }
                        is NavigationEvent.ToActorInfo -> {
                            navController.navigate("actor_info_screen")
                        }
                        is NavigationEvent.ToLogIn -> {
                            navController.navigate("log_in_screen")
                        }
                        is NavigationEvent.ToPhoneNumberSignIn -> {
                            navController.navigate("sign_in_with_phone_number_screen")
                        }
                        is NavigationEvent.ToCurrentlyTrendingTitles -> {
                            navController.navigate("currently_trending_screen")
                        }
                        is NavigationEvent.ToComingSoonTitles -> {
                            navController.navigate("new_releases_screen")
                        }
                        is NavigationEvent.ToTVShow -> {
                            navController.navigate("tvshows_screen")
                        }
                        is NavigationEvent.ToActors -> {
                            navController.navigate("actors_screen")
                        }
                        is NavigationEvent.ToMovie -> {
                            navController.navigate("movies_screen")
                        }
                        is NavigationEvent.ToCreateProfile -> {
                            navController.navigate("create_profile_screen")
                        }

                        NavigationEvent.None -> {}
                        else ->{}
                    }

                    viewModel.clearNavigation()
                }


            }
        }
    }
}


