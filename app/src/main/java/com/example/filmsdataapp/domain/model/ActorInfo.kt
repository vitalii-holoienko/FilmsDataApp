package com.example.filmsdataapp.domain.model

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ActorDetailResponse(
    val data: ActorData
)

@kotlinx.serialization.Serializable
data class ActorData(
    val name: ActorInfo
)

@kotlinx.serialization.Serializable
data class ActorInfo(
    val id: String,
    val nameText: NameText,
    val primaryImage: ActorPrimaryImage? = null,
    val height: Height? = null,
    val bio: Bio? = null,
    val akas: Akas? = null,
    val birthDate: BirthDate? = null,
    val birthLocation: DisplayableLocation? = null,
    val birthName: ActorNameText? = null,
    val deathStatus: String? = null,
    val deathDate: String? = null,
    val deathLocation: String? = null,
    val deathCause: String? = null,
    val spouses: List<Spouse>? = null,
    val officialLinks: OfficialLinks? = null
)

@kotlinx.serialization.Serializable
data class ActorNameText(val text: String)

@kotlinx.serialization.Serializable
data class ActorPrimaryImage(
    val id: String? = null,
    val url: String? = null,
    val height: Int? = null,
    val width: Int? = null,
)

@kotlinx.serialization.Serializable
data class Height(
    val measurement: Measurement,
    val displayableProperty: DisplayablePropertyText
)

@kotlinx.serialization.Serializable
data class Measurement(val value: Double, val unit: String)

@kotlinx.serialization.Serializable
data class DisplayablePropertyText(val value: PlainTextValue)

@kotlinx.serialization.Serializable
data class PlainTextValue(val plainText: String)

@kotlinx.serialization.Serializable
data class Bio(
    val id: String? = null,
    val text: PlainTextValue? = null
)

@kotlinx.serialization.Serializable
data class Akas(
    val total: Int? = null,
    val edges: List<AkaEdge>? = null
)

@kotlinx.serialization.Serializable
data class AkaEdge(val node: AkaNode)

@kotlinx.serialization.Serializable
data class AkaNode(val text: String)

@kotlinx.serialization.Serializable
data class BirthDate(
    val date: String? = null,
    val dateComponents: DateComponents? = null,
    val displayableProperty: DisplayablePropertyText? = null
)

@kotlinx.serialization.Serializable
data class DateComponents(
    val year: Int? = null,
    val partialYear: String? = null,
    val month: Int? = null,
    val day: Int? = null,
    val isBCE: Boolean? = null,
    val isApproximate: Boolean? = null
)

@kotlinx.serialization.Serializable
data class DisplayableLocation(
    val text: String? = null,
    val displayableProperty: DisplayablePropertyText? = null
)

@kotlinx.serialization.Serializable
data class Spouse(
    val spouse: SpouseDetail? = null,
    val attributes: List<SpouseAttribute>? = null,
    val current: Boolean? = null,
    val timeRange: TimeRange? = null
)

@kotlinx.serialization.Serializable
data class SpouseDetail(
    val asMarkdown: PlainTextValue? = null,
    val name: SpouseName? = null
)

@kotlinx.serialization.Serializable
data class SpouseName(
    val id: String,
    val nameText: NameText,
    val primaryImage: PrimaryImage? = null
)

@kotlinx.serialization.Serializable
data class SpouseAttribute(val id: String, val text: String)

@kotlinx.serialization.Serializable
data class TimeRange(
    val fromDate: DisplayableDate? = null,
    val toDate: DisplayableDate? = null,
    val displayableProperty: DisplayablePropertyText? = null
)

@kotlinx.serialization.Serializable
data class DisplayableDate(
    val displayableProperty: DisplayablePropertyText? = null,
)

@kotlinx.serialization.Serializable
data class OfficialLinks(
    val edges: List<ExternalLinkEdge>? = null,
)

@kotlinx.serialization.Serializable
data class ExternalLinkEdge(
    val node: ExternalLink? = null,
)

@Serializable
data class ExternalLink(
    val url: String? = null,
    val label: String? = null,
    val externalLinkRegion: String? = null
)