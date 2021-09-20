package com.navektest.search.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.navektest.search.databinding.SearchCategoryBinding
import com.navektest.search.model.SearchCategory
import com.navektest.search.view.adapter.viewholder.SearchCategoryViewHolder
import com.navektest.toolkit.view.BindableAdapter
import javax.inject.Inject

internal class SearchAdapter @Inject constructor() : ListAdapter<SearchCategory, SearchCategoryViewHolder>(SearchAdapterDiffCallback()),
                                    BindableAdapter<List<SearchCategory>> {
    @Volatile private var items: List<SearchCategory> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchCategoryBinding.inflate(inflater, parent, false)

        return SearchCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchCategoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    override fun setData(data: List<SearchCategory>?) {
        data ?: return
        items = data.toList()

        submitList(items)
    }

}

class SearchAdapterDiffCallback : DiffUtil.ItemCallback<SearchCategory>() {
    override fun areItemsTheSame(oldItem: SearchCategory, newItem: SearchCategory): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: SearchCategory, newItem: SearchCategory): Boolean {
        return oldItem.items == newItem.items
    }
}