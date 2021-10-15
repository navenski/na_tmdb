package com.navektest.search.transformer

import androidx.annotation.WorkerThread
import com.navektest.business.model.Media
import com.navektest.business.model.MediaPage
import com.navektest.business.model.MediaType
import com.navektest.business.resolver.PicturePathResolver
import com.navektest.business.search.SearchMulti
import com.navektest.search.R
import com.navektest.search.model.Page
import com.navektest.search.model.SearchCategory
import com.navektest.search.model.SearchData
import com.navektest.search.model.SearchItem
import com.navektest.toolkit.resolver.ResourceResolver
import javax.inject.Inject

internal class SearchDataTransformer @Inject constructor(
    private val resourceResolver: ResourceResolver) {

    @WorkerThread
    fun transform(mediaPage: MediaPage): SearchData {
        val page = Page(mediaPage.page, mediaPage.totalPages, mediaPage.totalResults)
        val items = mutableListOf<SearchCategory>()

        val map = mediaPage.items.groupBy { it.type }


        map.keys.forEach { key ->
            val mappedItems =
                map[key]!!.filter { it.hasPicture }
                    .map(::mapItem)
            if (mappedItems.isNotEmpty())
                items.add(SearchCategory(getTitle(key), mappedItems))
        }

        return SearchData(page, items)
    }

    private fun mapItem(item: Media): SearchItem {
        return SearchItem(item.id, item.title, item.defaultPicture.lowDefUrl)
    }

    private fun getTitle(mediaType: MediaType): String {
        return when (mediaType) {
            MediaType.MOVIE -> resourceResolver.getString(R.string.movie)
            MediaType.TV -> resourceResolver.getString(R.string.tv)
            MediaType.PERSON -> resourceResolver.getString(R.string.person)
            MediaType.UNKNOWN -> resourceResolver.getString(R.string.other)
        }
    }
}