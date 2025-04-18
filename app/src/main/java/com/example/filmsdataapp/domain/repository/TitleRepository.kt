package com.example.filmsdataapp.domain.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl

interface TitleRepository {
    suspend fun getReviewsById(id:String) : String
    suspend fun getTitleWithAppliedFilters(type:String, genre:String, averageRatingFrom:Int,
                                           averageRatingTo:Int, dateOfReleaseFrom:Int,
                                           dateOfReleaseTo: Int, language:String):String
}