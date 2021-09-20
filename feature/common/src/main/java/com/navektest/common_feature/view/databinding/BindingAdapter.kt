package com.navektest.common_feature.view.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.navektest.common_feature.viewmodel.State
import com.navektest.toolkit.view.BindableAdapter
import java.io.File

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("url")
    fun ImageView.setImageUrl(url: String) {
        Glide.with(this).clear(this)
        Glide.with(this)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .into(this)
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("items")
    fun <T> RecyclerView.setItems(data: T?) {
        (adapter as? BindableAdapter<T>)?.setData(data)
    }

    @JvmStatic
    @BindingAdapter("visibleOrGone")
    fun setVisibleOrGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}