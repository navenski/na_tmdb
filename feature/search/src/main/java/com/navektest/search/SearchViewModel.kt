package com.navektest.search

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navektest.business.model.ResultState
import com.navektest.common_feature.view.databinding.ObservableString
import com.navektest.search.router.SearchRouter
import com.navektest.usecase.search.MultiSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(private val multiSearchUseCase: MultiSearchUseCase) : ViewModel() {
    private var routerWeakRef: WeakReference<SearchRouter> = WeakReference<SearchRouter>(null)
    private var searchJob: Job? = null

    val searchText: ObservableString = ObservableString(onValueChanged = { launchSearch(it) })

    fun bindRouter(router: SearchRouter) {
        routerWeakRef = WeakReference(router)
    }

    private fun cancelPreviousSearchRequest() {
        val job = searchJob ?: return
        if (job.isActive)
            job.cancel()
    }

    private fun launchSearch(query: String) {
        cancelPreviousSearchRequest()
        searchJob = viewModelScope.launch {
            multiSearchUseCase.execute(query, 1)
                .collect {
                    when (it) {
                        is ResultState.Success -> Unit
                        is ResultState.Error -> TODO()
                    }
                }
        }
    }

    fun loadMore() {
    }
}