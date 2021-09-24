package com.navektest.tmdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor() : ViewModel() {
    private val countLiveData = MutableLiveData<Int>(0)

    val count: LiveData<Int> get() = countLiveData

    fun incrementCount() {
        var count = countLiveData.value ?: return
        count++
        countLiveData.value = count
    }

    fun decrementCount() {
        var count = countLiveData.value ?: return
        count--
        countLiveData.value = count
    }
}