package com.navektest.usecase.search

import com.navektest.business.model.MediaPage
import com.navektest.business.search.SearchRepository
import com.navektest.usecase.common.paging.PagingMediaHandlerFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MultiSearchUseCaseImpl @Inject constructor(private val searchRepository: SearchRepository,
                                                          factory: PagingMediaHandlerFactory) : MultiSearchUseCase {

    private val paging = factory.create<MultiSearchUseCase.Param>() {
        searchRepository.multiSearch(it.query, it.page)
    }

    override fun execute(parameter: MultiSearchUseCase.Param) = paging.execute(parameter)

    override fun observe(): Flow<MediaPage> = paging.observe()
}