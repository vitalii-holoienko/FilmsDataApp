package com.example.filmsdataapp.domain.repository

import android.net.Uri
import com.example.filmsdataapp.domain.model.ActivityData
import com.example.filmsdataapp.domain.model.Title

interface UserRepository {
    fun createUserAccount(nickname: String, description: String, image: Uri?, onSuccess: () -> Unit)
    fun setUserRatingForTitle(title: Title, rating: Float, where: String)
    fun getUserRatingForTitle(titleId: String, where: String, onResult: (Int?) -> Unit)
    fun changeUserAccount(nickname: String, description: String, image: Uri?, onSuccess: () -> Unit)

    fun getMonthlyCompletedStats(uid: String, onResult: (List<ActivityData>) -> Unit)
    fun checkIfUserHasTitleInLists(titleId: String, callback: (String) -> Unit)
    suspend fun deleteTitleFromAllLists(id: String)

    //------------------------------------------------------------------------------------//
    fun getUserNickname(callback: (String) -> Unit)

    fun getUserImage(callback: (Uri) -> Unit)

    fun getUserDescription(callback: (String) -> Unit)

    //------------------------------------------------------------------------------------//

    fun fetchUserHistory(onResult: (List<String>) -> Unit, onError: (Exception) -> Unit)

    //------------------------------------------------------------------------------------//

    fun addTitleToWatchingList(title: Title)

    fun addTitleToPlannedList(title: Title)

    fun addTitleToCompletedList(title: Title)

    fun addTitleToOnHoldList(title: Title)

    fun addTitleToDroppedList(title: Title)

    //------------------------------------------------------------------------------------//

    fun getDroppedTitles(callback: (List<Title>) -> Unit)

    fun getCompletedTitles(callback: (List<Title>) -> Unit)

    fun getWatchingTitles(callback: (List<Title>) -> Unit)

    fun getPlannedTitles(callback: (List<Title>) -> Unit)

}