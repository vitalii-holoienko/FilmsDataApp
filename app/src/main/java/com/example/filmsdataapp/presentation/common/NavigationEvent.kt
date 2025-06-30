package com.example.filmsdataapp.presentation.common

import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.Title

sealed class NavigationEvent {
    object ToProfile : NavigationEvent()
    object ToMain : NavigationEvent()
    object ToAuth : NavigationEvent()
    object OpenNav : NavigationEvent()
    object ToSearchTitle : NavigationEvent()
    data class ToTitle(val title: Title) : NavigationEvent()
    object ToActorInfo : NavigationEvent()

    object ToLogIn : NavigationEvent()

    object ToPhoneNumberSignIn : NavigationEvent()

    object ToCurrentlyTrendingTitles: NavigationEvent()

    object ToComingSoonTitles: NavigationEvent()

    object ToTVShow: NavigationEvent()

    object ToMovie: NavigationEvent()

    object ToUserHistory: NavigationEvent()
    object ToUserSettings: NavigationEvent()

    object ToActors: NavigationEvent()

    data class ToNews(val news: News) : NavigationEvent()

    object ToCreateProfile : NavigationEvent()

    object None : NavigationEvent()

    data class ToUserListOfTitles(val list:String) : NavigationEvent()
}