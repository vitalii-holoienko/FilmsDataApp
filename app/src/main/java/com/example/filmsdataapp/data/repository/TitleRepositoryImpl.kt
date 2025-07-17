package com.example.filmsdataapp.data.repository

import android.util.Log
import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.data.remote.NetworkService
import com.example.filmsdataapp.data.remote.api.TitleApi
import com.example.filmsdataapp.domain.model.ApiResponse
import com.example.filmsdataapp.domain.model.FilterStatus
import com.example.filmsdataapp.domain.model.FullResponse
import com.example.filmsdataapp.domain.model.Genre
import com.example.filmsdataapp.domain.model.Review
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.io.IOException

//enum class Genre{
//    DRAMA,COMEDY,DOCUMENTARY,
//    ACTION,ROMANCE,THRILLER,
//    CRIME,HORROR,ADVENTURE,
//    FAMILY,ANIMATION,REALITY_TV,
//    MYSTERY, FANTASY,HISTORY,BIOGRAPHY,
//    SCI_FI, SPORT,ADULT,WAR,
//}

class TitleRepositoryImpl : TitleRepository {
    override suspend fun getReviewsById(id: String): List<Review> = withContext(Dispatchers.IO) {
        val api = NetworkService.getInstance("https://imdb232.p.rapidapi.com/").create(TitleApi::class.java)
        try{
            val fr = api.getReviewsById(tt = id, apiKey = BuildConfig.RAPID_API_KEY, apiHost = BuildConfig.RAPID_API_HOST_TWO)
            fr.data?.title?.reviews?.edges?.map { it.node.toReview() } ?: emptyList<Review>()
        } catch (ex : IOException){
            Log.e("DEBUG", "No internet connection")
            emptyList<Review>()
        } catch (ex : Exception){
            Log.e("DEBUG", "unexpected error")
            emptyList<Review>()
        }
    }

    override suspend fun searchTitle(query: String): List<Title> = withContext(Dispatchers.IO) {
        val api = NetworkService.getInstance("https://imdb236.p.rapidapi.com/").create(TitleApi::class.java)
        try{
            api.searchTitle(query = query, apiKey = BuildConfig.RAPID_API_KEY, apiHost = BuildConfig.RAPID_API_HOST_ONE)
        } catch (ex : IOException){
            Log.e("DEBUG", "No internet connection")
            emptyList()
        } catch (ex : Exception){
            Log.e("DEBUG", "unexpected error")
            emptyList()
        }
    }
}