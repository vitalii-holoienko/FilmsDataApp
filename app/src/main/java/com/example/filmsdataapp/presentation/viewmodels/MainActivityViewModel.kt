package com.example.filmsdataapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsdataapp.data.repository.MoviesRepositoryImpl
import com.example.filmsdataapp.data.repository.NewsRepositoryImpl
import com.example.filmsdataapp.data.repository.TVShowsRepositoryImpl
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.usecase.GetCurrentlyTrendingMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetMostPopularMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetMostPopularTVShowsUseCase
import com.example.filmsdataapp.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {


    private val moviesRepository : MoviesRepository = MoviesRepositoryImpl()
    private val newsRepository : NewsRepository = NewsRepositoryImpl()
    private val TVShowsRepository : TVShowsRepository = TVShowsRepositoryImpl()

    private val _mostPopularMovies = MutableStateFlow("")
    private val _currentlyTrendingMovies = MutableStateFlow("")
    private val _news = MutableStateFlow("")
    private val _mostPopularTVShows = MutableStateFlow("")

    val mostPopularTVShows : StateFlow<String> = _mostPopularTVShows
    val news : StateFlow<String> = _news
    val mostPopularMovies: StateFlow<String> = _mostPopularMovies
    val currentlyTrendingMovies: StateFlow<String> = _currentlyTrendingMovies

    fun loadInitialData(){
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
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
            Log.d("TEKKEN", _mostPopularTVShows.value)

        }
    }
}
