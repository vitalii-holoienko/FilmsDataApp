package com.example.filmsdataapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val rows: Int,
    val numFound: Int,
    val results: List<Title>
)

@Serializable
data class Title(
    val id : String? = null,
    val url : String? = null,
    val primaryTitle : String? = null,
    val originalTitle : String? = null,
    val type : String? = null,
    val description : String? = null,
    val primaryImage : String? = null,
    val contentRating : String? = null,
    val startYear : Int? = null,
    val endYear : Int? = null,
    val releaseDate: String? = null,
    val language : String? = null,
    val interests : List<String>? = null,
    val externalLinks: List<String>? = null,
    val spokenLanguage : List<String>? = null,
    val filmingLocation : List<String>? = null,
    val budget : Long? = null,
    val grossWorldwide : Long? = null,
    val genres : List<String>? = null,
    val isAdult : Boolean? = null,
    val runtimeMinutes : Int? = null,
    val averageRating : Float? = null,
    val numVotes : Int? = null,

)