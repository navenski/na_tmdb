package com.navektest.common_feature.view.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.navektest.toolkit.view.BindableAdapter
import java.io.File

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("url")
    fun ImageView.setImageUrl(url:String) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(this)
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("items")
    fun <T> RecyclerView.setItems(data: T?) {
        (adapter as? BindableAdapter<T>)?.setData(data)
    }
}