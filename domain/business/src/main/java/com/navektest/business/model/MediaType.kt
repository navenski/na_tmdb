package com.navektest.business.model

enum class MediaType(val value: String) {
    MOVIE("movie"),
    PERSON("person"),
    TV("tv"),
    UNKNOWN("unknown");

    companion object {
        fun from(value: String): MediaType = values().firstOrNull { it.value == value.lowercase() } ?: UNKNOWN
    }
}