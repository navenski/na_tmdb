package com.navektest.business.resolver

interface PicturePathResolver {
    enum class Dimension(val value: Int) {
        SMALL(200),
        MEDIUM(500),
        LARGE(1000)
    }

    fun resolve(dimension: Dimension, relativePath: String): String
}