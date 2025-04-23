package com.example.filmsdataapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ActorResponse(
    val data: ActorData? = null,
    val status: Boolean? = null,
    val message: String? = null
)

@Serializable
data class ActorData(
    val name: Actor? = null
)

@Serializable
data class Actor(
    val id: String? = null,
    val nameText: NameText? = null,
    val primaryImage: ActorImage? = null,
    val bio: Bio? = null
)

@Serializable
data class NameText(
    val text: String? = null
)

@Serializable
data class ActorImage(
    val id: String? = null,
    val url: String? = null,
    val height: Int? = null,
    val width: Int? = null
)

@Serializable
data class Bio(
    val id: String? = null,
    val text: BioText? = null
)

@Serializable
data class BioText(
    val plainText: String? = null
)