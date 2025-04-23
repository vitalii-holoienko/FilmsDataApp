package com.example.filmsdataapp.domain.model

import kotlinx.serialization.Serializable
@Serializable
data class ArticleTitle(
    val plainText: String? = null
)

@Serializable
data class Image(
    val id: String? = null,
    val url: String? = null
)

@Serializable
data class Text(
    val plainText: String? = null
)

@Serializable
data class News(
    val id: String? = null,
    val byline: String? = null,
    val date: String? = null,
    val externalUrl: String? = null,
    val articleTitle: ArticleTitle? = null,
    val image: Image? = null,
    val text: Text? = null
)

@Serializable
data class Edge(
    val node: News
)

@Serializable
data class NewsConnection(
    val edges: List<Edge>
)

@Serializable
data class NewsData(
    val news: NewsConnection
)

@Serializable
data class RootNews(
    val data: NewsData
)