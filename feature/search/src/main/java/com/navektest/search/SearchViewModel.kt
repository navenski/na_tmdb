package com.navektest.search

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navektest.business.model.ResultState
import com.navektest.business.search.SearchMulti
import com.navektest.common_feature.view.databinding.ObservableString
import com.navektest.common_feature.viewmodel.State
import com.navektest.search.router.SearchRouter
import com.navektest.usecase.search.MultiSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(private val multiSearchUseCase: MultiSearchUseCase) : ViewModel() {
    private var routerWeakRef: WeakReference<SearchRouter> = WeakReference<SearchRouter>(null)
    private var searchJob: Job? = null
    private val stateSharedFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)
    private val mutableLiveData: MutableLiveData<State<SearchMulti>> = MutableLiveData()
    val searchText: ObservableString = ObservableString(onValueChanged = { launchSearch(it) })

    fun bindRouter(router: SearchRouter) {
        routerWeakRef = WeakReference(router)
    }

    private fun initSubscribtion() {
//        val flow: Flow<String> = stateSharedFlow
//        flow.filter { it.isNotEmpty() }.
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