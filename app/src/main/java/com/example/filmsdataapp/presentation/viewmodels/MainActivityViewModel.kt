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
import com.example.filmsdataapp.domain.model.FilterStatus
import com.example.filmsdataapp.domain.model.Genre
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.SORTED_BY
import com.example.filmsdataapp.domain.model.Title
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
    val filterStatus : FilterStatus = FilterStatus()

    private val moviesRepository : MoviesRepository = MoviesRepositoryImpl()
    private val newsRepository : NewsRepository = NewsRepositoryImpl()
    private val tvShowsRepository : TVShowsRepository = TVShowsRepositoryImpl()
    private val actorsRepository : ActorsRepository = ActorsRepositoryImpl()
    private val titleRepository : TitleRepository = TitleRepositoryImpl()

    private var _mostPopularMovies = MutableLiveData<List<Title>>()
    private var _comingSoonMovies = MutableLiveData<List<Title>>()
    private var _mostPopularTVShows = MutableLiveData<List<Title>>()
    private val _news = MutableLiveData<List<News>>()
    private val _actors = MutableLiveData<List<Actor>>()

    private val _currentlyTrendingMovies = MutableLiveData<List<Title>>()
    private val _titleWithAppliedFitlers = MutableStateFlow("")
    var _inititalTitleToDisplay = MutableLiveData<List<Title>>()
    val _titlesToDisplay = MutableLiveData<List<Title>>()

    val initialTitlesToDisplay: LiveData<List<Title>> get() = _inititalTitleToDisplay
    val titlesToDisplay: LiveData<List<Title>> get() = _titlesToDisplay
    val mostPopularMovies: LiveData<List<Title>> get() = _mostPopularMovies
    val comingSoonMovies: LiveData<List<Title>> get() = _comingSoonMovies
    val mostPopularTVShows: LiveData<List<Title>> get() = _mostPopularTVShows
    val news : LiveData<List<News>> get() = _news
    val currentlyTrendingMovies: LiveData<List<Title>> get() = _currentlyTrendingMovies
    val actors: LiveData<List<Actor>> get() = _actors

    fun loadInitialData(){
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
//                filterStatus.genre = Genre.ACTION
//                val result = GetTitleWithAppliedFiltersUseCase(titleRepository)
//                _titleWithAppliedFitlers.value = result.invoke(filterStatus)
//
//
//                val result6= GetMostPopularTVShowsUseCase(tvShowsRepository)
//                _mostPopularTVShows.value = result6.invoke()
//
//                val result2 = GetNewsUseCase(newsRepository)
//                _news.value = result2.invoke()
//
//                val result3 = GetMostPopularMoviesUseCase(moviesRepository)
//                _mostPopularMovies.value = result3.invoke()
//
//                val result4 = GetComingSoonMoviesUseCase(moviesRepository)
//                _comingSoonMovies.value = result4.invoke()
//
//
//
//
//                val result5 = GetActorsUseCase(actorsRepository)
//                _actors.value = result5.invoke()
//                Log.d("TEKKEN", _actors.value!!.size.toString())
//
                  val result1 = GetMostPopularTVShowsUseCase(tvShowsRepository)
                  _mostPopularTVShows.value = result1.invoke()
//
//                Log.d("TEKKEN", mostPopularTVShows.value!!.size.toString())



                val result0 = GetCurrentlyTrendingMoviesUseCase(moviesRepository)
                _currentlyTrendingMovies.value = result0.invoke()




            } catch (e: Exception) {
                Log.d("TEKKEN", e.message.toString())
            }
        }.invokeOnCompletion {


        }
    }
    fun applyFilter(){
        _titlesToDisplay.value = applyFiltersLogic(_inititalTitleToDisplay.value!!, filterStatus)
    }

    fun applyFiltersLogic(movies: List<Title>, filter: FilterStatus): List<Title> {
        return movies
            .asSequence()
            .filter { movie ->
                filter.type?.let { type ->
                    movie.type?.equals(type.name, ignoreCase = true)
                } ?: true
            }
            .filter { movie ->
                filter.genre?.let { genre ->
                    movie.genres?.any { it.equals(filter.genres[genre], ignoreCase = true) } ?: false
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

}
