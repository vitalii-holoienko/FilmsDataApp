package com.example.filmsdataapp.domain.model
//suspend fun getTitleWithAppliedFilters(type:String, genre:String, averageRatingFrom:Int, dateOfReleaseFrom:Int,
//                                       dateOfReleaseTo: Int, language:String):String
class FilterStatus(
    var genre : MutableList<Genre>? = null,
    var averageRationFrom:Int? = null,
    var dateOfReleaseFrom:Int? = null,
    var dateOfReleaseTo:Int? = null,
    var language:Language? = null,
    var sortedBy: SORTED_BY? = null

) {
    val genres = mapOf(
        Genre.DRAMA to "Drama",
        Genre.COMEDY to "Comedy",
        Genre.DOCUMENTARY to "Comedy",
        Genre.ACTION to "Action",
        Genre.ROMANCE to "Romance",
        Genre.THRILLER to "Thriller",
        Genre.CRIME to "Crime",
        Genre.HORROR to "Horror",
        Genre.ADVENTURE to "Adventure",
        Genre.FAMILY to "Family",
        Genre.ANIMATION to "Animation",
        Genre.REALITY_TV to "Reality-TV",
        Genre.MYSTERY to "Mystery",
        Genre.FANTASY to "Fantasy",
        Genre.HISTORY to "History",
        Genre.BIOGRAPHY to "Biography",
        Genre.SCI_FI to "Sci-fi",
        Genre.SPORT to "Sport",
        Genre.ADULT to "Adult",
        Genre.WAR to "War",
    )

}
enum class Genre{
    DRAMA,COMEDY,DOCUMENTARY,
    ACTION,ROMANCE,THRILLER,
    CRIME,HORROR,ADVENTURE,
    FAMILY,ANIMATION,REALITY_TV,
    MYSTERY, FANTASY,HISTORY,BIOGRAPHY,
    SCI_FI, SPORT,ADULT,WAR,
}

enum class Language{
    ENGLISH,RUSSIAN,FRENCH,GERMANY
}

enum class SORTED_BY{
    POPULARITY, RATING, ALPHABET, RANDOM, RELEASE_DATE,
}