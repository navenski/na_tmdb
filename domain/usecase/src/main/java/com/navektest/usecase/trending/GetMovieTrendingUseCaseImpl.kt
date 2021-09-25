package com.navektest.usecase.trending

import com.navektest.business.model.Media
import com.navektest.business.model.MediaPage
import com.navektest.business.trending.TrendingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetMovieTrendingUseCaseImpl @Inject constructor(private val repository: TrendingRepository) :
    GetMovieTrendingUseCase {
    override fun execute(): Flow<MediaPage> {
        return repository.getMovieTrending()
    }
}
