package com.navektest.usecase.common.transformer

import com.navektest.business.model.MediaPage
import javax.inject.Inject

//TODO add test
internal class MediaPageConcatZipper @Inject constructor() {

    fun zip(newPage: MediaPage, previousPage: MediaPage): MediaPage {
        if (newPage.page < FIRST_PAGE)
            throw  Exception("MediaPageConcatZipper: Page number cannot be less than 1")

        val isLoadMode = newPage.page > FIRST_PAGE
        if (!isLoadMode) {
            return newPage
        }

        if (previousPage.page <= FIRST_PAGE || (newPage.page - previousPage.page) != 1)
            return newPage

        val items =
            previousPage.items.toMutableList()
                .apply { addAll(newPage.items) }

        return MediaPage(newPage.page, items, newPage.totalPages, newPage.totalResults)
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}