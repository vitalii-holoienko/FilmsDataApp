package com.example.filmsdataapp.domain.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.model.FilterStatus
import com.example.filmsdataapp.domain.model.Review
import com.example.filmsdataapp.domain.model.Title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl

interface TitleRepository {
    suspend fun getReviewsById(id:String) : List<Review>
    suspend fun getTitleWithAppliedFilters(filterStatus: FilterStatus):String
    suspend fun searchTitle(query:String) : List<Title>

}