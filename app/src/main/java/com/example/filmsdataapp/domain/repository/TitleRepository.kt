package com.example.filmsdataapp.domain.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.model.FilterStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl

interface TitleRepository {
    suspend fun getReviewsById(id:String) : String
    suspend fun getTitleWithAppliedFilters(filterStatus: FilterStatus):String
}