package com.navektest.common_feature.view.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("text")
    fun setText(view: TextView, text: String) {            //Some data removed
        if (view.text != text)
            view.text = text
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "text", event = "")
    fun getText(view: TextView): String = view.text.toString()
}