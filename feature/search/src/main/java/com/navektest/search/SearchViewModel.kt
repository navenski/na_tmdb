package com.navektest.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navektest.business.model.ResultState
import com.navektest.business.search.SearchMulti
import com.navektest.common_feature.view.databinding.ObservableString
import com.navektest.common_feature.viewmodel.State
import com.navektest.search.model.SearchData
import com.navektest.search.router.SearchRouter
import com.navektest.search.transformer.SearchDataTransformer
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import com.navektest.usecase.search.MultiSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject

@FlowPreview
@HiltViewModel
internal class SearchViewModel @Inject constructor(private val multiSearchUseCase: MultiSearchUseCase,
                                                   private val dispatcherProvider: CoroutineDispatcherProvider,
                                                   private val searchDataTransformer: SearchDataTransformer) : ViewModel() {
    private var routerWeakRef: WeakReference<SearchRouter> = WeakReference<SearchRouter>(null)

    private val stateSharedFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)

    private val mutableLiveData: MutableLiveData<State<SearchData>> = MutableLiveData()
    fun getLiveData(): LiveData<State<SearchData>> = mutableLiveData

    val searchText: ObservableString =
        ObservableString(onValueChanged = { viewModelScope.launch { stateSharedFlow.emit(it.trim()) } })

    fun bindRouter(router: SearchRouter) {
        routerWeakRef = WeakReference(router)
    }

    init {
        viewModelScope.launch { observeChange() }
    }

    private suspend fun observeChange() {

        val flow: Flow<String> = stateSharedFlow
            .distinctUntilChanged()
            .onEach {
                if (it.isEmpty()) mutableLiveData.value =
                    State.idle()
            }
        flow.filterNot { it.isEmpty() }
            .onEach {
                mutableLiveData.value = State.loading()
            }
            .debounce(500)
            .flowOn(dispatcherProvider.main())
            .flatMapLatest { multiSearchUseCase.execute(it, 1) }
            .mapLatest(::mapToState)
            .flowOn(dispatcherProvider.default())
            .distinctUntilChanged()
            .collectLatest {
                mutableLiveData.value = it
            }
    }

    private fun mapToState(resultState: ResultState<SearchMulti>): State<SearchData> {
        return when (resultState) {
            is ResultState.Success -> State.success(searchDataTransformer.transform(resultState.data))
            is ResultState.Error -> State.error(resultState.exception)
        }
    }
}