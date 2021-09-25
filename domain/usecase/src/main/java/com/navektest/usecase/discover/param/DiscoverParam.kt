package com.navektest.usecase.discover.param

data class DiscoverParam(val page: Int = FIRST_PAGE, val refresh: Boolean = false) {
    fun isLoadMore() = page > FIRST_PAGE

    companion object {
        private const val FIRST_PAGE = 1
    }
}