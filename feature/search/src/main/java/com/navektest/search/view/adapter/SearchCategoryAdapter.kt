package com.navektest.search.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.navektest.search.databinding.SearchItemBinding
import com.navektest.search.model.SearchItem
import com.navektest.search.view.adapter.viewholder.SearchItemViewHolder
import com.navektest.toolkit.view.BindableAdapter

internal class SearchCategoryAdapter : RecyclerView.Adapter< SearchItemViewHolder>(),
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

        val diffCallback = SearchCategoryDiffCallback(items, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = data.toList()
        diffResult.dispatchUpdatesTo(this)
    }
}

class SearchCategoryDiffCallback(private val oldItems: List<SearchItem>, private val newItems: List<SearchItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]
}