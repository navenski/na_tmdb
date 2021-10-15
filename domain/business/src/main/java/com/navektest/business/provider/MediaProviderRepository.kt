package com.navektest.business.provider

import com.navektest.business.model.MediaProvider
import kotlinx.coroutines.flow.Flow

interface MediaProviderRepository {
    fun getAll() : Flow<List<MediaProvider>>
    fun get(id: Int) : Flow<MediaProvider>
}