package com.navektest.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navektest.business.model.MediaPage
import com.navektest.common_feature.view.databinding.ObservableString
import com.navektest.common_feature.viewmodel.State
import com.navektest.search.model.SearchData
import com.navektest.search.router.SearchRouter
import com.navektest.search.transformer.SearchDataTransformer
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import com.navektest.usecase.search.MultiSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject

@FlowPreview
@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
internal class SearchViewModel @Inject constructor(private val multiSearchUseCase: MultiSearchUseCase,
                                                   private val dispatcherProvider: CoroutineDispatcherProvider,
                                                   private val searchDataTransformer: SearchDataTransformer) : ViewModel() {
    private var routerWeakRef: WeakReference<SearchRouter> = WeakReference<SearchRouter>(null)

    private val searchActionStateFlow: MutableSharedFlow<SearchAction> = MutableSharedFlow(replay = 1)
    private val stateFlow: MutableSharedFlow<State<SearchData>> = MutableSharedFlow(replay = 1)

    private val mutableLiveData: MutableLiveData<State<SearchData>> = MutableLiveData()
    fun getLiveData(): LiveData<State<SearchData>> = mutableLiveData

    val searchText: ObservableString =
        ObservableString(onValueChanged = {
            viewModelScope.launch {
                searchActionStateFlow.emit(SearchAction(it.trim(),
                                                        1))
            }
        })

    init {
        viewModelScope.launch { observeChange() }
    }

    fun bindRouter(router: SearchRouter) {
        routerWeakRef = WeakReference(router)
    }

    private suspend fun observeChange() {

        val flow = searchActionStateFlow
            .distinctUntilChanged()
            .onEach {
                if (it.searchTerm.isEmpty())
                    stateFlow.emit(State.idle())
            }
            .filterNot { it.searchTerm.isEmpty() }
            .onEach { stateFlow.emit(State.loading(mutableLiveData.value?.data)) }
            .debounce(500)
            .onEach { multiSearchUseCase.execute(parameter = MultiSearchUseCase.Param(it.searchTerm, it.page)) }
            .flatMapLatest { multiSearchUseCase.observe() }
            .map(::mapToState)
            .flowOn(dispatcherProvider.default())


        merge(flow, stateFlow).collectLatest {
            mutableLiveData.value = it
        }
    }


    private fun mapToState(resultState: MediaPage): State<SearchData> {
        return State.success(searchDataTransformer.transform(resultState))
    }

    private data class SearchAction(val searchTerm: String, val page: Int)
}
