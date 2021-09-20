package com.navektest.search.transformer

import androidx.annotation.WorkerThread
import com.navektest.business.resolver.PicturePathResolver
import com.navektest.business.search.SearchMulti
import com.navektest.search.R
import com.navektest.search.model.Page
import com.navektest.search.model.SearchCategory
import com.navektest.search.model.SearchData
import com.navektest.search.model.SearchItem
import com.navektest.toolkit.resolver.ResourceResolver
import javax.inject.Inject

internal class SearchDataTransformer @Inject constructor(private val pathResolver: PicturePathResolver,
                                                         private val resourceResolver: ResourceResolver) {

    @WorkerThread
    fun transform(search: SearchMulti): SearchData {
        val page = Page(search.page, search.total_pages, search.total_results)
        val items = mutableListOf<SearchCategory>()

        search.items.forEach { category ->
            val mappedItems = category.items.filter { hasPhoto(it) }.map(::mapItem)
            if (mappedItems.isNotEmpty())
            items.add(SearchCategory(getTitle(category.mediaType), mappedItems))
        }

        return SearchData(page, items)
    }

    private fun mapItem(item: SearchMulti.Item): SearchItem {
        var path = listOf(item.poster_path, item.profile_path, item.backdrop_path).firstOrNull { it.isNotEmpty() } ?: ""

        if (path.isNotEmpty())
            path = pathResolver.resolve(PicturePathResolver.Dimension.SMALL, path)

        return SearchItem(item.id, item.title, path)
    }

    private fun hasPhoto(item: SearchMulti.Item) : Boolean {
        return item.poster_path.isNotEmpty() || item.profile_path.isNotEmpty() || item.backdrop_path.isNotEmpty()
    }

    private fun getTitle(mediaType: SearchMulti.MediaType): String {
        return when (mediaType) {
            SearchMulti.MediaType.MOVIE -> resourceResolver.getString(R.string.movie)
            SearchMulti.MediaType.TV -> resourceResolver.getString(R.string.tv)
            SearchMulti.MediaType.PERSON -> resourceResolver.getString(R.string.person)
            SearchMulti.MediaType.UNKNOWN -> resourceResolver.getString(R.string.other)
        }
    }
}