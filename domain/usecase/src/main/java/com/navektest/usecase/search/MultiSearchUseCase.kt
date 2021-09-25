package com.navektest.usecase.search

import com.navektest.business.model.MediaPage
import com.navektest.business.model.ResultState
import com.navektest.business.search.SearchMulti
import com.navektest.usecase.common.ObservableUseCase
import kotlinx.coroutines.flow.Flow


interface MultiSearchUseCase : ObservableUseCase<MultiSearchUseCase.Param, MediaPage> {
    data class Param(val query: String, val page: Int)
}