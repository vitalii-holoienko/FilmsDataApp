package com.example.filmsdataapp.data.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl

class TitleRepositoryImpl : TitleRepository {
    override suspend fun getReviewsById(id: String): String = withContext(Dispatchers.IO){
        NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/title/get-user-reviews?order=DESC&spoiler=EXCLUDE&tt=${id}&sortBy=HELPFULNESS_SCORE", 2)
    }

    override suspend fun getTitleWithAppliedFilters(
        type: String,
        genre: String,
        averageRatingFrom: Int,
        averageRatingTo: Int,
        dateOfReleaseFrom: Int,
        dateOfReleaseTo: Int,
        language: String
    ): String = withContext(Dispatchers.IO){
        var url = "https://imdb236.p.rapidapi.com/imdb/search?rows=100&sortField=id"

        when(type){
            "movie" -> url = url.toHttpUrl().newBuilder().addQueryParameter("type", "movie").toString()
            "tvSeries" -> url = url.toHttpUrl().newBuilder().addQueryParameter("type", "tvSeries").toString()
        }

        if(genre != ""){
                url = url.toHttpUrl().newBuilder().addQueryParameter("genre", genre).toString()
        }
//

        if(averageRatingFrom != -1){
            url = url.toHttpUrl().newBuilder().addQueryParameter("averageRatingFrom", averageRatingFrom.toString()).toString()
        }
        if(averageRatingTo != -1){
            url = url.toHttpUrl().newBuilder().addQueryParameter("averageRatingTo", averageRatingTo.toString()).toString()
        }

        if(dateOfReleaseFrom != -1){
            url = url.toHttpUrl().newBuilder().addQueryParameter("startYearFrom", dateOfReleaseFrom.toString()).toString()
        }
        if(dateOfReleaseTo != -1){
            url = url.toHttpUrl().newBuilder().addQueryParameter("startYearTo", dateOfReleaseTo.toString()).toString()
        }
        NetworkHelper.makeRequest(url,1)
    }


}