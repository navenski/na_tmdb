package com.navektest.usecase.discover

import com.navektest.business.discover.DiscoverRepository
import com.navektest.business.model.MediaPage
import com.navektest.usecase.common.ObservableUseCase
import com.navektest.usecase.common.UseCase
import com.navektest.usecase.common.paging.PagingMediaHandlerFactory
import com.navektest.usecase.common.paging.PagingMediaRequest
import com.navektest.usecase.discover.param.DiscoverParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DiscoverTvUseCase : ObservableUseCase<DiscoverParam, MediaPage>

internal class DiscoverTvUseCaseImpl @Inject constructor(private val repository: DiscoverRepository,
                                                         factory: PagingMediaHandlerFactory) : DiscoverTvUseCase {
    private val paging = factory.create<DiscoverParam> { repository.getMovie(it.page) }

    override fun execute(parameter: DiscoverParam) = paging.execute(parameter)
    override fun observe(): Flow<MediaPage> = paging.observe()
}