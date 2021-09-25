package com.navektest.data.trending

import com.navektest.business.model.MediaPage
import com.navektest.business.model.MediaType
import com.navektest.business.trending.TrendingRepository
import com.navektest.data.trending.remote.TrendingRemoteDataSource
import com.navektest.data.common.remote.transformer.MediaPageTransformer
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TrendingRepositoryImpl @Inject constructor(
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val dataSource: TrendingRemoteDataSource,
    private val mediaTransformer: MediaPageTransformer) : TrendingRepository {

    override fun getMovieTrending(): Flow<MediaPage> {
        return dataSource.getMovie()
            .map { mediaTransformer.transform(MediaType.MOVIE, it) }
            .flowOn(dispatcherProvider.default())
    }

    override fun getTvTrending(): Flow<MediaPage> {
        return dataSource.getTv()
            .map { mediaTransformer.transform(MediaType.TV, it) }
            .flowOn(dispatcherProvider.default())
    }
}