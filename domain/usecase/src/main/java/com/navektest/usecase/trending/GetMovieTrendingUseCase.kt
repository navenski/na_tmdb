package com.navektest.usecase.trending

import com.navektest.business.model.MediaPage
import kotlinx.coroutines.flow.Flow

interface GetMovieTrendingUseCase {
    fun execute(): Flow<MediaPage>
}