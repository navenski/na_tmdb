package com.navektest.data.search.remote.response

import com.google.gson.annotations.SerializedName

data class SearchMultiResponse (

    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<Item>,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("total_results") val total_results : Int
){
    data class Item (
       @SerializedName("adult") val adult: Boolean ?= null,
       @SerializedName("backdrop_path") val backdrop_path: String?= null,
       @SerializedName("gender") val gender: Int?= null,
       @SerializedName("genre_ids") val genre_ids: List<Int>?= null,
       @SerializedName("id") val id: Int?= null,
       @SerializedName("known_for") val known_for: List<KnownFor>?= null,
       @SerializedName("known_for_department") val known_for_department: String?= null,
       @SerializedName("media_type") val media_type: String?= null,
       @SerializedName("name") val name: String?= null,
       @SerializedName("original_language") val original_language: String?= null,
       @SerializedName("original_title") val original_title: String?= null,
       @SerializedName("overview") val overview: String?= null,
       @SerializedName("popularity") val popularity: Double?= null,
       @SerializedName("poster_path") val poster_path: String?= null,
       @SerializedName("profile_path") val profile_path: String?= null,
       @SerializedName("release_date") val release_date: String?= null,
       @SerializedName("title") val title: String?= null,
       @SerializedName("video") val video: Boolean?= null,
       @SerializedName("vote_average") val vote_average: Int?= null,
       @SerializedName("vote_count") val vote_count: Int?= null
    )

    data class KnownFor (

        @SerializedName("adult") val adult : Boolean?= null,
        @SerializedName("backdrop_path") val backdrop_path : String?= null,
        @SerializedName("genre_ids") val genre_ids : List<Int>?= null,
        @SerializedName("id") val id : Int?= null,
        @SerializedName("media_type") val media_type : String?= null,
        @SerializedName("original_language") val original_language : String?= null,
        @SerializedName("original_title") val original_title : String?= null,
        @SerializedName("overview") val overview : String?= null,
        @SerializedName("poster_path") val poster_path : String?= null,
        @SerializedName("release_date") val release_date : String?= null,
        @SerializedName("title") val title : String?= null,
        @SerializedName("video") val video : Boolean?= null,
        @SerializedName("vote_average") val vote_average : Double?= null,
        @SerializedName("vote_count") val vote_count : Int?= null
    )
}