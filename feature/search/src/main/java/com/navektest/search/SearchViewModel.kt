package com.navektest.search

import androidx.lifecycle.ViewModel
import com.navektest.search.router.SearchRouter
import java.lang.ref.WeakReference

internal class SearchViewModel : ViewModel() {
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