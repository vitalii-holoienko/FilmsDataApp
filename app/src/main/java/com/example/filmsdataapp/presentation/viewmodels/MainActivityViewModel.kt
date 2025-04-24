package com.example.filmsdataapp.presentation.viewmodels

import Movie
import TVShow
import android.util.Log
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
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.repository.ActorsRepository
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import com.example.filmsdataapp.domain.usecase.GetActorsUseCase
import com.example.filmsdataapp.domain.usecase.GetComingSoonMoviesUseCase
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
    private val tvShowsRepository : TVShowsRepository = TVShowsRepositoryImpl()
    private val actorsRepository : ActorsRepository = ActorsRepositoryImpl()
    private val titleRepository : TitleRepository = TitleRepositoryImpl()

    private var _mostPopularMovies = MutableLiveData<List<Movie>>()
    private var _comingSoonMovies = MutableLiveData<List<Movie>>()
    private var _mostPopularTVShows = MutableLiveData<List<TVShow>>()
    private val _currentlyTrendingMovies = MutableStateFlow("")
    private val _news = MutableLiveData<List<News>>()
    private val _actors = MutableLiveData<List<Actor>>()
    private val _titleWithAppliedFitlers = MutableStateFlow("")
    val mostPopularMovies: LiveData<List<Movie>> get() = _mostPopularMovies
    val comingSoonMovies: LiveData<List<Movie>> get() = _comingSoonMovies
    val mostPopularTVShows: LiveData<List<TVShow>> get() = _mostPopularTVShows
    val news : LiveData<List<News>> get() = _news
    val currentlyTrendingMovies: StateFlow<String> = _currentlyTrendingMovies
    val actors: LiveData<List<Actor>> get() = _actors
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

//                val result1 = GetMostPopularMoviesUseCase(moviesRepository)
//                _mostPopularMovies.value = result1.invoke()
//
//                val result2 = GetComingSoonMoviesUseCase(moviesRepository)
//                _comingSoonMovies.value = result2.invoke()

                val result1 = GetActorsUseCase(actorsRepository)
                _actors.value = result1.invoke()
                Log.d("TEKKEN", _actors.value!!.size.toString())

//                  val result1 = GetMostPopularTVShowsUseCase(tvShowsRepository)
//                  _mostPopularTVShows.value = result1.invoke()
//
//                Log.d("TEKKEN", mostPopularTVShows.value!!.size.toString())



//                val result = GetCurrentlyTrendingMoviesUseCase(moviesRepository)
//                _currentlyTrendingMovies.value = result.invoke()



            } catch (e: Exception) {
                Log.d("TEKKEN", e.message.toString())
            }
        }.invokeOnCompletion {


        }
    }


}
