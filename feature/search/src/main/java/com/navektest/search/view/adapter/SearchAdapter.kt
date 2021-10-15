package com.navektest.search.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.navektest.search.databinding.SearchCategoryBinding
import com.navektest.search.model.SearchCategory
import com.navektest.search.view.adapter.viewholder.SearchCategoryViewHolder
import com.navektest.toolkit.view.BindableAdapter
import javax.inject.Inject

internal class SearchAdapter @Inject constructor() : RecyclerView.Adapter< SearchCategoryViewHolder>(),
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
        val searchCategoryData = data ?: emptyList()

        val diffCallback = SearchAdapterDiffCallback(items, searchCategoryData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = searchCategoryData.toList()
        diffResult.dispatchUpdatesTo(this)
    }

}

class SearchAdapterDiffCallback(private val oldItems: List<SearchCategory>, private val newItems: List<SearchCategory>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int  = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean  = oldItems[oldItemPosition].title == newItems[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean  = oldItems[oldItemPosition] == newItems[newItemPosition]
}