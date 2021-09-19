package com.navektest.business.search

data class SearchMulti(
    val page: Int,
    val items: List<Category>,
    val total_pages: Int,
    val total_results: Int
){
    enum class MediaType(val value: String) {
        MOVIE("movie"),
        PERSON("person"),
        TV("tv"),
        UNKNOWN("unknown");

        companion object{
            fun from(value: String) : MediaType = values().firstOrNull { it.value == value } ?: UNKNOWN
        }
    }

    data class Category(val mediaType: MediaType, val items: List<Item>)

    data class Item(
        val adult: Boolean,
        val backdrop_path: String,
        val gender: Int,
        val genre_ids: List<Int>,
        val id: Int,
        val known_for: List<KnownFor>,
        val known_for_department: String,
        val media_type: String,
        val name: String,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val profile_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
    )

    data class KnownFor(
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>,
        val id: Int,
        val media_type: String,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
    )
}