package com.navektest.search

import androidx.lifecycle.ViewModel
import com.navektest.search.router.SearchRouter
import com.navektest.usecase.search.MultiSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    }
}