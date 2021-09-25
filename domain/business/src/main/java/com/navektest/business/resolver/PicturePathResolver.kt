package com.navektest.business.resolver

//TODO remove it from Business layer
//TODO add resolver for picture image
interface PicturePathResolver {
    enum class Dimension(val value: Int) {
        SMALL(200),
        MEDIUM(500),
        LARGE(1280)
    }

    fun resolve(dimension: Dimension, relativePath: String): String
}