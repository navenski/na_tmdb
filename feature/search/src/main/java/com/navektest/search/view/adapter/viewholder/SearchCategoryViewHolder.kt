package com.navektest.search.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.navektest.search.databinding.SearchCategoryBinding
import com.navektest.search.model.SearchCategory
import com.navektest.search.view.adapter.SearchCategoryAdapter

class SearchCategoryViewHolder(private val binding: SearchCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.searchCategoryRecycler.adapter = SearchCategoryAdapter()
    }

    fun bind(model: SearchCategory) {
        binding.model = model
    }
}