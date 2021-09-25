package com.navektest.feed.viewmodel.transformer

import androidx.annotation.WorkerThread
import com.navektest.business.model.Media
import com.navektest.business.resolver.PicturePathResolver
import com.navektest.feed.R
import com.navektest.feed.model.FeedCategory
import com.navektest.feed.model.FeedItem
import com.navektest.toolkit.resolver.ResourceResolver
import javax.inject.Inject

internal class FeedCategoryTransformer @Inject constructor() {

    @WorkerThread
    fun transform(categoryTitle: String, data: List<Media>): FeedCategory {
        return FeedCategory(categoryTitle, data.map { it.transformItem() })
    }

    private fun Media.transformItem(): FeedItem {
        return FeedItem(id, title, poster.lowDefUrl)
    }
}