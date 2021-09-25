package com.navektest.data.discover

import com.navektest.business.discover.DiscoverRepository
import com.navektest.business.model.MediaPage
import com.navektest.data.discover.remote.DiscoverRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DiscoverRepositoryImpl @Inject constructor(private val remoteDataSource: DiscoverRemoteDataSource) :
    DiscoverRepository {

    //TODO add local datasource for database support: save to database when page 1 data succeed
    override fun getMovie(page: Int): Flow<MediaPage> {
        return remoteDataSource.getMovie(page)
    }

    override fun getTv(page: Int): Flow<MediaPage> {
        return remoteDataSource.getMovie(page)
    }
}