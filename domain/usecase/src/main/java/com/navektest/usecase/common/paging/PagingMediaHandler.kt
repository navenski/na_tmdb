package com.navektest.usecase.common.paging

import com.navektest.business.model.MediaPage
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import com.navektest.usecase.common.ObservableUseCase
import com.navektest.usecase.common.transformer.MediaPageConcatZipper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip

internal fun interface PagingMediaRequest<P> {
    fun execute(parameter: P): Flow<MediaPage>
}

internal class PagingMediaHandler<in P>(private val zipper: MediaPageConcatZipper,
                                        private val dispatcherProvider: CoroutineDispatcherProvider,
                                        private val request:PagingMediaRequest<P>)  : ObservableUseCase<P, MediaPage> {

    private val sharedFlow = MutableSharedFlow<P>(replay = 1)
    //Init with an empty page
    @Volatile private var lastMediaPage: MediaPage = MediaPage(1, emptyList(), 1, 0)

    private val mediaPageFlow by lazy {
        sharedFlow.flatMapMerge { parameter ->
            request.execute(parameter)
        }
            .zip(getLastMediaPage(), zipper::zip)
            .onEach {
                lastMediaPage = it
            }
            .flowOn(dispatcherProvider.default())
    }


    private fun getLastMediaPage(): Flow<MediaPage> = flow { emit(lastMediaPage) }

    override fun execute(parameter: P) {
        request
    }

    override fun observe(): Flow<MediaPage>  = mediaPageFlow
}