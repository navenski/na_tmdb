package com.navektest.search.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.navektest.search.SearchViewModel
import com.navektest.search.databinding.FragmentSearchBinding
import com.navektest.search.router.SearchRouter
import com.navektest.search.view.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject internal lateinit var router: SearchRouter
    @Inject internal lateinit var adapter: SearchAdapter

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = FragmentSearchBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.searchRecycler.adapter = adapter
        viewModel.bindRouter(router)
        return binding.root
    }
}