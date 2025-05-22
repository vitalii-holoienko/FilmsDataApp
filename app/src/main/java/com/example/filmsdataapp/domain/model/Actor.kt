package com.example.filmsdataapp.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class ActorsResponse(
    val data: Data
)

@Serializable
data class Data(
    val topMeterNames: TopMeterNames
)

@Serializable
data class TopMeterNames(
    val edges: List<ActorEdge>
)

@Serializable
data class ActorEdge(
    val node: Actor
)

@Serializable
data class Actor(
    val id: String,
    val nameText: NameText,
    val primaryImage: PrimaryImage? = null,
    val meterRanking: MeterRanking
)

@Serializable
data class NameText(
    val text: String
)

@Serializable
data class PrimaryImage(
    val id: String,
    val url: String,
    val height: Int,
    val width: Int
)

@Serializable
data class MeterRanking(
    val currentRank: Int,
    val rankChange: RankChange
)

@Serializable
data class RankChange(
    val changeDirection: String,
    val difference: Int
)