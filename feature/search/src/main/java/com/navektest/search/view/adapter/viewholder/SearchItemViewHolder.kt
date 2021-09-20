package com.navektest.search.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.navektest.search.databinding.SearchItemBinding
import com.navektest.search.model.SearchItem

internal class SearchItemViewHolder(private val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: SearchItem) {
        binding.model = model
    }
}