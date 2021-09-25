package com.navektest.data.common.resolver

import com.navektest.business.model.Picture
import com.navektest.business.resolver.PicturePathResolver
import javax.inject.Inject

internal class PictureResolver @Inject constructor(private val picturePathResolver: PicturePathResolver) {

    fun resolve(path: String): Picture {
        return Picture(path, picturePathResolver.resolve(PicturePathResolver.Dimension.SMALL, path),
                       picturePathResolver.resolve(PicturePathResolver.Dimension.MEDIUM, path),
                       picturePathResolver.resolve(PicturePathResolver.Dimension.LARGE, path))
    }
}