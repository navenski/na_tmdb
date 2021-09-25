package com.navektest.usecase.common.paging

import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import com.navektest.usecase.common.transformer.MediaPageConcatZipper
import javax.inject.Inject

internal class PagingMediaHandlerFactory @Inject constructor(private val zipper: MediaPageConcatZipper,
                                                             private val dispatcherProvider: CoroutineDispatcherProvider) {
    fun <P> create(pagingRequest: PagingMediaRequest<P>): PagingMediaHandler<P> =
        PagingMediaHandler<P>(zipper, dispatcherProvider, pagingRequest)
}