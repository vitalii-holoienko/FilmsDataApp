package com.example.filmsdataapp.data.repository

import android.util.Log
import com.example.filmsdataapp.data.network.NetworkHelper
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
        val jsonStr = NetworkHelper.makeRequest(
            "https://imdb232.p.rapidapi.com/api/title/get-user-reviews?order=DESC&spoiler=EXCLUDE&tt=${id}&sortBy=HELPFULNESS_SCORE",
            2
        ) ?: return@withContext emptyList()

        if (jsonStr.isBlank()) return@withContext emptyList()

        val json = Json { ignoreUnknownKeys = true }
        val parsed = json.decodeFromString<FullResponse>(jsonStr)
        val reviews = parsed.data?.title?.reviews?.edges?.map { it.node.toReview() } ?: emptyList()

        return@withContext reviews
    }

    override suspend fun getTitleWithAppliedFilters(filterStatus: FilterStatus): String {
        TODO("Not yet implemented")
    }

//    override suspend fun getTitleWithAppliedFilters(
//        filterStatus: FilterStatus
//    ): String = withContext(Dispatchers.IO){
//        var url = "https://imdb236.p.rapidapi.com/api//imdb/search?rows=100&sortField=id"
//
//        if(filterStatus.genre!=null){
//            val selectedGenres = filterStatus.genre?.map { index -> filterStatus.genres[index] } ?: emptyList()
//
//            if (selectedGenres.isNotEmpty()) {
//                val genresJsonArray = selectedGenres.joinToString(
//                    separator = ",",
//                    prefix = "[\"",
//                    postfix = "\"]"
//                ) { it!! }
//
//                url = url.toHttpUrl().newBuilder()
//                    .addQueryParameter("genres", genresJsonArray)
//                    .toString()
//            }
//        }
//
//        if(filterStatus.averageRationFrom!=null){
//            url = url.toHttpUrl().newBuilder().addQueryParameter("averageRatingFrom", filterStatus.averageRationFrom.toString()).toString()
//        }
//
//        if(filterStatus.dateOfReleaseFrom != null){
//            url = url.toHttpUrl().newBuilder().addQueryParameter("startYearFrom", filterStatus.dateOfReleaseFrom.toString()).toString()
//        }
//        if(filterStatus.dateOfReleaseTo != null){
//            url = url.toHttpUrl().newBuilder().addQueryParameter("startYearTo", filterStatus.dateOfReleaseTo.toString()).toString()
//        }
//        NetworkHelper.makeRequest(url,1)
//    }

    override suspend fun getTitlesReleasedInCertainYear(year: Int): List<Title> = withContext(Dispatchers.IO) {
        val json = Json {
            ignoreUnknownKeys = true
        }

        val jsonString = NetworkHelper.makeRequest(
            "https://imdb236.p.rapidapi.com/api/imdb/search?rows=100&startYearFrom=$year&sortOrder=ASC&sortField=id", 1
        )

        if (jsonString.isNullOrBlank()) return@withContext emptyList()

        val response = json.decodeFromString<ApiResponse>(jsonString)
        return@withContext response.results
    }


    override suspend fun searchTitle(query: String): List<Title> = withContext(Dispatchers.IO) {
        val json = Json { ignoreUnknownKeys = true }

        val jsonString = NetworkHelper.makeRequest(
            "https://imdb236.p.rapidapi.com/api/imdb/autocomplete?query=${query}", 1
        ) ?: return@withContext emptyList()

        if (jsonString.isBlank()) return@withContext emptyList()

        val response = json.decodeFromString<List<Title>>(jsonString)

        return@withContext response
    }
}