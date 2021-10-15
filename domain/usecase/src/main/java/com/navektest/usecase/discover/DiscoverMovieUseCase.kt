package com.navektest.usecase.discover

import com.navektest.business.discover.DiscoverRepository
import com.navektest.business.model.MediaPage
import com.navektest.usecase.common.ObservableUseCase
import com.navektest.usecase.common.paging.PagingMediaHandlerFactory
import com.navektest.usecase.discover.param.DiscoverParam
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Hot Flow.
 * collect must be called once
 */
interface DiscoverMovieUseCase : ObservableUseCase<DiscoverParam, MediaPage>

@OptIn(FlowPreview::class)
internal class DiscoverMovieUseCaseImpl @Inject constructor(private val repository: DiscoverRepository,
                                                            pagingFactory: PagingMediaHandlerFactory) :
    DiscoverMovieUseCase {
    private val paging = pagingFactory.create<DiscoverParam> { repository.getMovie(it.page) }

    override fun invoke(parameter: DiscoverParam) = paging.invoke(parameter)
    override fun flow(): Flow<MediaPage> = paging.flow()
}