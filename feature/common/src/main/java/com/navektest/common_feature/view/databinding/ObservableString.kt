package com.navektest.common_feature.view.databinding

import androidx.databinding.Observable
import androidx.databinding.ObservableField

class ObservableString(value: String = "", onValueChanged: ((String) -> Unit)? = null) : ObservableField<String>(value) {
    init {
        this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                onValueChanged?.invoke((sender as ObservableString).get()!!)
            }
        })
    }
}