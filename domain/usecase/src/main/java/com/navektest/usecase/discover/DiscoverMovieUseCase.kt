package com.navektest.usecase.discover

import com.navektest.business.discover.DiscoverRepository
import com.navektest.business.model.MediaPage
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import com.navektest.usecase.common.ObservableUseCase
import com.navektest.usecase.common.transformer.MediaPageConcatZipper
import com.navektest.usecase.discover.param.DiscoverParam
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

/**
 * Hot Flow.
 * collect must be called once
 */
interface DiscoverMovieUseCase : ObservableUseCase<DiscoverParam, MediaPage>

@OptIn(FlowPreview::class)
internal class DiscoverMovieUseCaseImpl @Inject constructor(private val repository: DiscoverRepository,
                                                            private val dispatcherProvider: CoroutineDispatcherProvider,
                                                            private val zipper: MediaPageConcatZipper
) :
    DiscoverMovieUseCase {

    private val sharedFlow = MutableSharedFlow<DiscoverParam>(replay = 1)

    //Init with an empty page
    @Volatile private var lastMediaPage: MediaPage = MediaPage(1, emptyList(), 1, 0)

    private val mediaPageFlow by lazy {
        sharedFlow.flatMapMerge { parameter ->
            repository.getMovie(page = parameter.page)
        }
            .zip(getLastMediaPage(), zipper::zip)
            .onEach {
                lastMediaPage = it
            }
            .flowOn(dispatcherProvider.default())
    }

    private fun getLastMediaPage(): Flow<MediaPage> = flow { emit(lastMediaPage) }

    override fun execute(parameter: DiscoverParam) {
        sharedFlow.tryEmit(parameter)
    }

    override fun observe(): Flow<MediaPage> = mediaPageFlow
}
