package com.navektest.tmdb

import android.app.Application
import com.bumptech.glide.Glide
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TmdbApplication : Application() {
    override fun onLowMemory() {
        super.onLowMemory()

        Glide.get(this)
            .clearMemory()
    }
}