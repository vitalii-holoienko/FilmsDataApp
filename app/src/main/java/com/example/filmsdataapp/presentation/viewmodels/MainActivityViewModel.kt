package com.example.filmsdataapp.presentation.viewmodels

import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.data.repository.MovieRepositoryImpl
import com.example.filmsdataapp.domain.repository.MovieRepository
import com.example.filmsdataapp.domain.usecase.GetMostPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {


    private val repository : MovieRepository = MovieRepositoryImpl()

    private val _mostPopularMovies = MutableStateFlow("")
    val mostPopularMovies: StateFlow<String> = _mostPopularMovies

    fun loadInitialData(){
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            try {
                val result = GetMostPopularMoviesUseCase(repository)
                _mostPopularMovies.value = result.invoke()

            } catch (e: Exception) {
                _mostPopularMovies.value = "Error: ${e.message}"
            }
        }.invokeOnCompletion {
            Log.d("TEKKEN", _mostPopularMovies.value)

        }
    }
}
