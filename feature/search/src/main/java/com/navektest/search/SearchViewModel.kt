package com.navektest.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navektest.business.model.ResultState
import com.navektest.search.router.SearchRouter
import com.navektest.usecase.search.MultiSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(private val multiSearchUseCase: MultiSearchUseCase) : ViewModel() {
    private var routerWeakRef: WeakReference<SearchRouter> = WeakReference<SearchRouter>(null)
    private var searchText: String = ""

    fun bindRouter(router: SearchRouter) {
        routerWeakRef = WeakReference(router)
    }

    fun setSearchText(text: String) {

    }

    fun launchSearch() {
        viewModelScope.launch {
            multiSearchUseCase.execute("marvel", 1)
                .collect {
                when(it) {
                    is ResultState.Success -> Unit
                    is ResultState.Error -> TODO()
                }
            }
        }
    }
}