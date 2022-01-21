package com.navektest.feed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navektest.common_feature.navigation.compose.screen.Screen
import com.navektest.common_feature.navigation.compose.screen.TmdbRouter
import com.navektest.common_feature.viewmodel.State
import com.navektest.feed.R
import com.navektest.feed.model.FeedCategory
import com.navektest.feed.viewmodel.transformer.FeedCategoryTransformer
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import com.navektest.toolkit.resolver.ResourceResolver
import com.navektest.usecase.trending.GetMovieTrendingUseCase
import com.navektest.usecase.trending.GetTvTrendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FeedViewModel @Inject constructor(
    private val router: TmdbRouter,
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val movieUseCase: GetMovieTrendingUseCase,
    private val tvUseCase: GetTvTrendingUseCase,
    private val transformer: FeedCategoryTransformer,
    private val resourceResolver: ResourceResolver,
) : ViewModel() {

    private val mediaCategoryMutableLiveData = MutableLiveData<State<List<FeedCategory>>>()
    val mediaCategoryLiveData: LiveData<State<List<FeedCategory>>> get() = mediaCategoryMutableLiveData

    init {
        viewModelScope.launch { observeMedia() }
    }

    private suspend fun observeMedia() {
        getMovieCategory()
            .zip(getTvCategory())
            { f1, f2 ->
                State.success(listOf(f1, f2))
            }
            .flowOn(dispatcherProvider.default())
            .onStart { emit(State.loading()) }
            .catch { //Todo notify error
            }
            .collect {
                mediaCategoryMutableLiveData.value = it
            }
    }

    private fun getMovieCategory(): Flow<FeedCategory> {
        return movieUseCase.execute()
            .map { transformer.transform(resourceResolver.getString(R.string.movie), it.items) }
    }

    private fun getTvCategory(): Flow<FeedCategory> {
        return tvUseCase.execute()
            .map { transformer.transform(resourceResolver.getString(R.string.movie), it.items) }
    }

     fun testNavigation(){
        router.navigateTo(Screen.Details)
    }
}