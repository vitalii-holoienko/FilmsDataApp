package com.example.filmsdataapp.domain.model

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ComingSoonResponse(
    val data: ComingSoonData
)

@kotlinx.serialization.Serializable
data class ComingSoonData(
    val comingSoon: ComingSoonEdges
)

@kotlinx.serialization.Serializable
data class ComingSoonEdges(
    val edges: List<ComingSoonEdge>
)

@kotlinx.serialization.Serializable
data class ComingSoonEdge(
    val node: ComingSoonNode?
)

@Serializable
data class ComingSoonNode(
    val id: String
)