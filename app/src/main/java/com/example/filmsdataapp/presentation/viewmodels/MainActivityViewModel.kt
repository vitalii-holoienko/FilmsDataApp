package com.example.filmsdataapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsdataapp.data.repository.ActorsRepositoryImpl
import com.example.filmsdataapp.data.repository.MoviesRepositoryImpl
import com.example.filmsdataapp.data.repository.NewsRepositoryImpl
import com.example.filmsdataapp.data.repository.TVShowsRepositoryImpl
import com.example.filmsdataapp.data.repository.TitleRepositoryImpl
import com.example.filmsdataapp.domain.repository.ActorsRepository
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import com.example.filmsdataapp.domain.usecase.GetActorsUseCase
import com.example.filmsdataapp.domain.usecase.GetCurrentlyTrendingMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetMostPopularMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetMostPopularTVShowsUseCase
import com.example.filmsdataapp.domain.usecase.GetNewsUseCase
import com.example.filmsdataapp.domain.usecase.GetTitleWithAppliedFiltersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {


    private val moviesRepository : MoviesRepository = MoviesRepositoryImpl()
    private val newsRepository : NewsRepository = NewsRepositoryImpl()
    private val tVShowsRepository : TVShowsRepository = TVShowsRepositoryImpl()
    private val actorsRepository : ActorsRepository = ActorsRepositoryImpl()
    private val titleRepository : TitleRepository = TitleRepositoryImpl()

    private val _mostPopularMovies = MutableStateFlow("")
    private val _currentlyTrendingMovies = MutableStateFlow("")
    private val _news = MutableStateFlow("")
    private val _mostPopularTVShows = MutableStateFlow("")
    private val _actors = MutableStateFlow("")
    private val _titleWithAppliedFitlers = MutableStateFlow("")

    val mostPopularTVShows : StateFlow<String> = _mostPopularTVShows
    val news : StateFlow<String> = _news
    val mostPopularMovies: StateFlow<String> = _mostPopularMovies
    val currentlyTrendingMovies: StateFlow<String> = _currentlyTrendingMovies
    val actors: StateFlow<String> = _actors
    val titleWithAppliedFitlers:  StateFlow<String> = _titleWithAppliedFitlers

    fun loadInitialData(){
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {

//                val result = GetTitleWithAppliedFiltersUseCase(titleRepository)
//                _titleWithAppliedFitlers.value = result.invoke(type = "movie", genre = "Drama", averageRatingFrom = -1, averageRatingTo = -1, dateOfReleaseFrom = 1995, dateOfReleaseTo = 2025, language = "")

//                val result = GetMostPopularTVShowsUseCase(TVShowsRepository)
//                _mostPopularTVShows.value = result.invoke()

//                val result = GetNewsUseCase(newsRepository)
//                _news.value = result.invoke()

//                val result = GetMostPopularMoviesUseCase(moviesRepository)
//                _mostPopularMovies.value = result.invoke()

//                val result = GetCurrentlyTrendingMoviesUseCase(moviesRepository)
//                _currentlyTrendingMovies.value = result.invoke()



            } catch (e: Exception) {
                _mostPopularMovies.value = "Error: ${e.message}"
            }
        }.invokeOnCompletion {
            Log.d("TEKKEN", _titleWithAppliedFitlers.value)

        }
    }
}
