package com.navektest.data.common.remote.transformer

import androidx.annotation.WorkerThread
import com.navektest.business.model.Media
import com.navektest.business.model.MediaPage
import com.navektest.business.model.MediaType
import com.navektest.data.common.remote.response.MediaResponse
import com.navektest.data.common.resolver.PictureResolver
import javax.inject.Inject

internal class MediaPageTransformer @Inject constructor(private val pictureResolver: PictureResolver) {
    @WorkerThread
    fun transform(defaultMediaType: MediaType,  response: MediaResponse): MediaPage {
        return MediaPage(
            page = response.page,
            items = response.results.map { it.transformItem(defaultMediaType) },
            totalPages = response.total_pages,
            totalResults = response.total_results
        )
    }

    private fun MediaResponse.MediaItem.transformItem(defaultMediaType: MediaType): Media {
        return Media(id = id ?: 0,
                     title = title ?: name ?: "",
                     MediaType.from(media_type ?: defaultMediaType.value),
                     pictureResolver.resolve(poster_path ?: ""),
                     pictureResolver.resolve(backdrop_path ?: ""))
    }
}