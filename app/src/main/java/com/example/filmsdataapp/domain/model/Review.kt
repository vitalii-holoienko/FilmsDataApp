package com.example.filmsdataapp.domain.model

import android.text.BoringLayout
import kotlinx.serialization.Serializable


@Serializable
data class FullResponse(
    val data: TitleWrapper? = null
)

@Serializable
data class TitleWrapper(
    val title: TitleData
)

@Serializable
data class TitleData(
    val reviews: ReviewsContainer
)

@Serializable
data class DataContainer(
    val reviews: ReviewsContainer
)

@Serializable
data class ReviewsContainer(
    val edges: List<ReviewEdge>
)

@Serializable
data class ReviewEdge(
    val node: ReviewNode
)

@Serializable
data class ReviewText(
    val originalText: TextValue? = null,
    val plainText: String? = null
)

@Serializable
data class TextValue(
    val plainText: String? = null
)

@Serializable
data class Summary(
    val originalText: String? = null
)
@Serializable
data class ReviewNode(
    val author: Author? = null,
    val helpfulness: Helpfulness? = null,
    val score: Double? = null,
    val text: ReviewText? = null,
    val summary: Summary? = null,
    val submissionDate: String? = null,
    val spoiler: Boolean? = null
) {
    fun toReview(): Review {
        return Review(
            nickName = author?.nickName,
            upvotes = helpfulness?.upVotes,
            downvotes = helpfulness?.downVotes,
            score = score,
            originalText = text?.originalText?.plainText,
            plainText = text?.plainText,
            summary = summary?.originalText,
            submissionDate = submissionDate,
            spoiler = spoiler
        )
    }
}

@Serializable
data class Author(
    val nickName: String? = null
)

@Serializable
data class Helpfulness(
    val upVotes: Int? = null,
    val downVotes: Int? = null
)



@Serializable
data class Review(
    val nickName: String? = null,
    val upvotes: Int? = null,
    val downvotes: Int? = null,
    val score: Double? = null,
    val originalText: String? = null,
    val plainText: String? = null,
    val summary: String? = null,
    val submissionDate: String? = null,
    val spoiler: Boolean? = null
)