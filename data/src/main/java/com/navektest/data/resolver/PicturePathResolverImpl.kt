package com.navektest.data.resolver

import com.navektest.business.resolver.PicturePathResolver
import javax.inject.Inject

internal class PicturePathResolverImpl @Inject constructor(): PicturePathResolver{

    override fun resolve(dimension: PicturePathResolver.Dimension, relativePath: String): String {
        return resolvePath(dimension = dimension.value, relativePath)
    }


    companion object{
        fun resolvePath(dimension: Int, relativePath: String) : String {
            return "https://image.tmdb.org/t/p/w${dimension}${relativePath}"
        }
    }

}