package com.navektest.usecase.trending

import com.navektest.business.model.Media
import com.navektest.business.model.MediaPage
import kotlinx.coroutines.flow.Flow

interface GetTvTrendingUseCase {
    fun execute() : Flow<MediaPage>
}