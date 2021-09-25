package com.navektest.usecase.discover

import com.navektest.business.discover.DiscoverRepository
import com.navektest.business.model.MediaPage
import com.navektest.usecase.common.UseCase
import com.navektest.usecase.discover.param.DiscoverParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DiscoverTvUseCase : UseCase<DiscoverParam, MediaPage>

internal class DiscoverTvUseCaseImpl @Inject constructor(private val repository: DiscoverRepository) : DiscoverTvUseCase {
    override fun execute(parameter: DiscoverParam): Flow<MediaPage> = repository.getTv(parameter.page)
}