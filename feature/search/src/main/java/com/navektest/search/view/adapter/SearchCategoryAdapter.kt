package com.navektest.search.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.navektest.search.databinding.SearchItemBinding
import com.navektest.search.model.SearchItem
import com.navektest.search.view.adapter.viewholder.SearchItemViewHolder
import com.navektest.toolkit.view.BindableAdapter

internal class SearchCategoryAdapter : ListAdapter<SearchItem, SearchItemViewHolder>(SearchCategoryDiffCallback()),
                                       BindableAdapter<List<SearchItem>> {

    @Volatile private var items: List<SearchItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchItemViewHolder(SearchItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun setData(data: List<SearchItem>?) {
        data ?: return
        items = data.toList()

        submitList(items)
    }

}

class SearchCategoryDiffCallback: DiffUtil.ItemCallback<SearchItem>() {
    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem == newItem
    }
}
