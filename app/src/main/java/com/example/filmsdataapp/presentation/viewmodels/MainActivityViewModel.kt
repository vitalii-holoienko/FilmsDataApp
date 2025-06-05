package com.example.filmsdataapp.presentation.viewmodels

import android.app.Activity
import android.content.Context
import androidx.credentials.CredentialManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.credentials.exceptions.ClearCredentialException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsdataapp.data.repository.ActorsRepositoryImpl
import com.example.filmsdataapp.data.repository.MoviesRepositoryImpl
import com.example.filmsdataapp.data.repository.NewsRepositoryImpl
import com.example.filmsdataapp.data.repository.TVShowsRepositoryImpl
import com.example.filmsdataapp.data.repository.TitleRepositoryImpl
import com.example.filmsdataapp.domain.model.Actor
import com.example.filmsdataapp.domain.model.ActorInfo
import com.example.filmsdataapp.domain.model.FilterStatus
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.Review
import com.example.filmsdataapp.domain.model.SORTED_BY
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.ActorsRepository
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import com.example.filmsdataapp.domain.usecase.GetActorInfoByIdUseCase
import com.example.filmsdataapp.domain.usecase.GetActorsUseCase
import com.example.filmsdataapp.domain.usecase.GetComingSoonMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetCurrentlyTrendingMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetMostPopularMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetNewsUseCase
import com.example.filmsdataapp.domain.usecase.GetReviewsByIdUseCase
import com.example.filmsdataapp.domain.usecase.SearchTitleUseCase
import com.example.filmsdataapp.presentation.common.NavigationEvent
import com.example.filmsdataapp.presentation.utils.NetworkMonitor
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivityViewModel(private val context: Context) : ViewModel() {
    val filterStatus : FilterStatus = FilterStatus()

    private var networkMonitor = NetworkMonitor(context)
    val isConnected: StateFlow<Boolean> = networkMonitor.networkStatus

    var credentialManager : CredentialManager? = null
    lateinit var firebaseAuth : FirebaseAuth
    var user : FirebaseUser? = null

    private val moviesRepository : MoviesRepository = MoviesRepositoryImpl()
    private val newsRepository : NewsRepository = NewsRepositoryImpl()
    private val tvShowsRepository : TVShowsRepository = TVShowsRepositoryImpl()
    private val actorsRepository : ActorsRepository = ActorsRepositoryImpl()
    private val titleRepository : TitleRepository = TitleRepositoryImpl()

    private val _navigation = MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navigation: StateFlow<NavigationEvent> = _navigation

    var clickedTitle = MutableLiveData<Title>()
    var clickedNews = MutableLiveData<News>()




    var searchEnded = MutableLiveData<Boolean>()
    var userNotSingedIn = MutableLiveData<Boolean>()

    var showWarningInAuthenticationScreen = MutableLiveData<Boolean>()

    var showWarningInPhoneNumberScreen = MutableLiveData<Boolean>(false)
    var warningInPhoneNumberScreen = MutableLiveData<String>("")

    var showSignInUsingGoogleOption = MutableLiveData<Boolean>()

    var userSuccessfullySignedIn =  MutableLiveData<Boolean>()

    var appGotUserPhoneNumber = MutableLiveData(false)

    var warningInAuthenticationScreen = MutableLiveData<String>()

    var enteredPhoneNumber = MutableLiveData("")



    var recievedActorInfo = MutableLiveData<Boolean>(false)
    private var _mostPopularMovies = MutableLiveData<List<Title>>()
    private var _comingSoonMovies = MutableLiveData<List<Title>>()
    private var _mostPopularTVShows = MutableLiveData<List<Title>>()
    private val _searchedTitles = MutableLiveData<List<Title>>()
    private val _news = MutableLiveData<List<News>>()
    private val _actors = MutableLiveData<List<Actor>>()
    private var _titlesReleasedIn2025 = MutableLiveData<List<Title>>()
    private var _titlesReleasedIn2024= MutableLiveData<List<Title>>()
    var searchedQuery = MutableLiveData<String>()

    var displayedActorInfo = MutableLiveData<ActorInfo>(null)
    private val _currentlyTrendingMovies = MutableLiveData<List<Title>>()
    private val _titleWithAppliedFitlers = MutableStateFlow("")
    var _inititalTitleToDisplay = MutableLiveData<List<Title>>()

    val _titlesToDisplay = MutableLiveData<List<Title>>()
    val _reviewsToDisplay = MutableLiveData<List<Review>>()
    val initialTitlesToDisplay: LiveData<List<Title>> get() = _inititalTitleToDisplay
    val titlesToDisplay: LiveData<List<Title>> get() = _titlesToDisplay
    val titlesReleasedIn2025: LiveData<List<Title>> get() = _titlesReleasedIn2025
    val titlesReleasedIn2024: LiveData<List<Title>> get() = _titlesReleasedIn2024
    val searchedTitles: LiveData<List<Title>> get() = _searchedTitles
    val reviewsToDisplay: LiveData<List<Review>> get() = _reviewsToDisplay
    val mostPopularMovies: LiveData<List<Title>> get() = _mostPopularMovies
    val comingSoonMovies: LiveData<List<Title>> get() = _comingSoonMovies
    val mostPopularTVShows: LiveData<List<Title>> get() = _mostPopularTVShows


    val news : LiveData<List<News>> get() = _news
    val currentlyTrendingMovies: LiveData<List<Title>> get() = _currentlyTrendingMovies
    val actors: LiveData<List<Actor>> get() = _actors


    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.


            if (e is FirebaseAuthInvalidCredentialsException) {
                showWarningInPhoneNumberScreen.value = true
                warningInPhoneNumberScreen.value = "Verification code is incorrect"
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
            }

            showWarningInPhoneNumberScreen.value = true
            warningInPhoneNumberScreen.value = "Something went wrong, try again later"
            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d("TEKKEN", "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token


        }
    }

    //NAVIGATION

    fun clearNavigation() {
        _navigation.value = NavigationEvent.None
    }

    fun onNewsClicked(news: News) {
        _navigation.value = NavigationEvent.ToNews(news)
    }

    fun onTitleClicked(title: Title) {
        _navigation.value = NavigationEvent.ToTitle(title)
    }

    fun onCurrentlyTrendingTitlesClicked(){
        _navigation.value = NavigationEvent.ToCurrentlyTrendingTitles
    }

    fun onComingSoonTitlesClicked(){
        _navigation.value = NavigationEvent.ToComingSoonTitles
    }

    fun onTVShowsClicked(){
        _navigation.value = NavigationEvent.ToTVShow
    }

    fun onMoviesClicked(){
        _navigation.value = NavigationEvent.ToMovie
    }

    fun onActorsClicked(){
        _navigation.value = NavigationEvent.ToActors
    }

    fun onMainClicked(){
        _navigation.value = NavigationEvent.ToMain
    }

    fun onProfileClicked(){
        _navigation.value = NavigationEvent.ToProfile
    }

    fun onSearchTitleClicked(){
        _navigation.value = NavigationEvent.ToSearchTitle
    }

    fun onMenuClicked(){
        _navigation.value = NavigationEvent.OpenNav
    }

    fun onAuthClicked(){
        _navigation.value = NavigationEvent.ToAuth
    }
    //-------------------------------------------------------------------------------
    fun loadInitialData(){
        loadMovies()
    }

    fun startInternetObserve(){
        networkMonitor = NetworkMonitor(context)
    }

    fun checkIfUserIsSignedIn(){
        val currentUser = firebaseAuth.currentUser
        userNotSingedIn.value = currentUser == null

    }


    fun inputWasSuccessfullyValidated(email : String, password: String) : Pair<Boolean, String?>{
        val pair = validateUserAuthenticationInput(email, password)
        if(!pair.first){
            showWarningInAuthenticationScreen.value = true
            warningInAuthenticationScreen.value = pair.second.second
            return false to pair.second.first
        }
        return true to null

    }
    private fun validateUserAuthenticationInput(email: String, password: String): Pair<Boolean,Pair<String, String?>> {

        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")

        val forbiddenCharsRegex = Regex("[\\s\"',;]")

        if (email.isBlank()) {
            return Pair<Boolean,Pair<String, String?>>(false, "email" to "Email cannot be empty.")
        }
        if (!emailRegex.matches(email)) {
            return Pair<Boolean,Pair<String, String?>>(false, "email" to "Please enter a valid email address.")
        }
        if (forbiddenCharsRegex.containsMatchIn(email)) {
            return Pair<Boolean,Pair<String, String?>>(false, "email" to "Email contains invalid characters.")
        }

        if (password.isBlank()) {
            return Pair<Boolean,Pair<String, String?>>(false, "password" to "Password cannot be empty.")
        }
        if (password.length < 8) {
            return Pair<Boolean,Pair<String, String?>>(false, "password" to "Password must be at least 8 characters long.")
        }
        if (forbiddenCharsRegex.containsMatchIn(password)) {
            return Pair<Boolean,Pair<String, String?>>(false, "password" to "Password contains invalid characters.")
        }

        val containsLetter = password.any { it.isLetter() }
        val containsDigit = password.any { it.isDigit() }

        if (!containsLetter || !containsDigit) {
            return Pair<Boolean,Pair<String, String?>>(false, "password" to "Password must contain at least one letter and one number.")
        }

        return Pair<Boolean,Pair<String, String?>>(true, "" to null)
    }
    private fun loadMovies() {
        viewModelScope.launch {
            try {
                val result2 = GetNewsUseCase(newsRepository)
                _news.value = result2.invoke()
            } catch (e: Exception) {
                Log.d("TEKKEN", e.message.toString())
            }

            try {
                val result3 = GetMostPopularMoviesUseCase(moviesRepository)
                _mostPopularMovies.value = result3.invoke()
            } catch (e: Exception) {
                Log.d("TEKKEN", e.message.toString())
            }
            try {
                val result4 = GetComingSoonMoviesUseCase(moviesRepository)
                _comingSoonMovies.value = result4.invoke()
            } catch (e: Exception) {
                Log.d("TEKKEN", e.message.toString())
            }
            try {
                val result5 = GetActorsUseCase(actorsRepository)
                _actors.value = result5.invoke()
                Log.d("TEKKEN", _actors.value!!.size.toString() + "ACTORS")
            } catch (e: Exception) {
                Log.d("TEKKEN", e.message.toString())
            }



//            try {
//                val result1 = GetMostPopularTVShowsUseCase(tvShowsRepository)
//                _mostPopularTVShows.value = result1.invoke()
//            } catch (e: Exception) {
//                Log.d("TEKKEN", e.message.toString())
//            }
            try {
                val result0 = GetCurrentlyTrendingMoviesUseCase(moviesRepository)
                _currentlyTrendingMovies.value = result0.invoke()
            } catch (e: Exception) {
                Log.d("TEKKEN", e.message.toString())
            }
//            try {
//                val result10 = GetTitlesReleasedInCertainYear(titleRepository)
//                _titlesReleasedIn2025.value = result10.invoke(2025)
//            } catch (e: Exception) {
//                Log.d("TEKKEN", e.message.toString())
//            }
//            try {
//                val result11 = GetTitlesReleasedInCertainYear(titleRepository)
//                _titlesReleasedIn2025.value = result11.invoke(2024)
//            } catch (e: Exception) {
//                Log.d("TEKKEN", e.message.toString())
//            }
        }.invokeOnCompletion {}
    }


    //LOGIC CONNECTED WITH PHONE NUMBER AUTHENTICATION

    fun startPhoneNumberVerification(activity: Activity) {

        appGotUserPhoneNumber.value = true
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(enteredPhoneNumber.value!!) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyCode(code: String) {
        if(code.isEmpty()){
            showWarningInPhoneNumberScreen.value = true
            warningInPhoneNumberScreen.value = "Please enter verification code"
            return
        }
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    fun verifyPhoneNumber(number : String) : Boolean{
        if(number.isEmpty()){
            showWarningInPhoneNumberScreen.value = true
            warningInPhoneNumberScreen.value = "Please enter your phone number"
            return false
        }
        return true


    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Log.d("TEKKEN", "signInWithCredential:success")

                    val isNewUser = task.result?.additionalUserInfo?.isNewUser == true
                    if (isNewUser) {

                        Log.d("Auth", "New user registered")
                    } else {

                        Log.d("Auth", "User signed in")
                    }

                    userNotSingedIn.value = false
                    userSuccessfullySignedIn.value = true

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TEKKEN", "signInWithCredential:failure", task.exception)

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    fun searchTitle(query:String){
        viewModelScope.launch {
            val result = SearchTitleUseCase(titleRepository)
            _searchedTitles.value = result.invoke(query)
        }.invokeOnCompletion {
            searchEnded.value = true
            Log.d("TEKKEN", "SEARCHED TITLES " + searchedTitles.value!!.size.toString())
        }

    }


    fun signOut() {
        // Firebase sign out
        firebaseAuth.signOut()
        userNotSingedIn.value = true

        // When a user signs out, clear the current user credential state from all credential providers.
        viewModelScope.launch {
            try {
                val clearRequest = ClearCredentialStateRequest()
                credentialManager?.clearCredentialState(clearRequest)

            } catch (e: ClearCredentialException) {
                Log.e("TEKKEN", "Couldn't clear user credentials: ${e.localizedMessage}")
            }
        }


    }


    fun stopSearching(){
        searchEnded.value = false
    }

    fun getTitleReviews(id:String){
        viewModelScope.launch {
            val result = GetReviewsByIdUseCase(titleRepository)
            val list = result.invoke(id)
            _reviewsToDisplay.value = list
        }
    }

    fun getActorInfo(id:String){
        try{
            viewModelScope.launch {
                val r = GetActorInfoByIdUseCase(actorsRepository)
                displayedActorInfo.value = r.invoke(id)
            }.invokeOnCompletion {
                recievedActorInfo.value = true
            }
        }catch (e : Exception){

        }
    }

    fun applyFilter(){
        _titlesToDisplay.value = applyFiltersLogic(_inititalTitleToDisplay.value!!, filterStatus)
    }

    private fun applyFiltersLogic(movies: List<Title>, filter: FilterStatus): List<Title> {
        return movies
            .asSequence()
            .filter { movie ->
                filter.genre?.let { genre ->
                    movie.genres?.any { it.equals(filter.genres[genre.get(0)], ignoreCase = true) } ?: false
                } ?: true
            }
            .filter { movie ->
                filter.averageRationFrom?.let { from ->
                    movie.averageRating?.toInt()?.let { it >= from } ?: false
                } ?: true
            }
            .filter { movie ->
                val year = movie.startYear
                val fromOk = filter.dateOfReleaseFrom?.let { year != null && year >= it } ?: true
                val toOk = filter.dateOfReleaseTo?.let { year != null && year <= it } ?: true
                fromOk && toOk
            }
            .sortedWith { a, b ->
                when (filter.sortedBy) {
                    SORTED_BY.POPULARITY -> (b.numVotes ?: 0).compareTo(a.numVotes ?: 0)
                    SORTED_BY.RATING -> (b.averageRating ?: 0f).compareTo(a.averageRating ?: 0f)
                    SORTED_BY.ALPHABET -> (a.primaryTitle ?: "").compareTo(b.primaryTitle ?: "")
                    SORTED_BY.RELEASE_DATE -> (b.startYear ?: 0).compareTo(a.startYear ?: 0)
                    SORTED_BY.RANDOM -> listOf(-1, 1).random()
                    null -> 0
                }
            }
            .toList()
    }

    fun handleSignIn(credential: Credential, componentActivity: ComponentActivity) {
        // Check if credential is of type Google ID
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            // Create Google ID Token
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            // Sign in to Firebase with using the token
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken, componentActivity)
        } else {
            Log.w("TEKKEN", "Credential is not of type Google ID!")
        }
    }

    fun firebaseAuthWithGoogle(idToken: String, componentActivity: ComponentActivity) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(componentActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TEKKEN", "signInWithCredential:success")
                    userNotSingedIn.value = false
                    userSuccessfullySignedIn.value = true
                } else {
                    // If sign in fails, display a message to the user
                    Log.w("TEKKEN", "signInWithCredential:failure", task.exception)
                }
            }
    }

}
