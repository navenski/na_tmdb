package com.navektest.tmdb.glide

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import java.io.InputStream
import java.util.concurrent.TimeUnit

@Excludes(OkHttpLibraryGlideModule::class)
@GlideModule
class GlideModule : AppGlideModule() {

    /**
     * These configurations, they are probably the default configs,
     * but we prefer put them here for further modifications,
     * such as [MemorySizeCalculator] and [InternalCacheDiskCacheFactory]
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val diskCacheSizeBytes = DISK_CACHE_SIZE_MB * 1024 * 1024 // we put 250mb even the default is already 250mb, for further changes.
        val memorySizeCalculator = MemorySizeCalculator.Builder(context).build()
        builder.apply {
            setMemoryCache(LruResourceCache(memorySizeCalculator.memoryCacheSize.toLong()))
            setBitmapPool(LruBitmapPool(memorySizeCalculator.bitmapPoolSize.toLong()))
            setArrayPool(LruArrayPool(memorySizeCalculator.arrayPoolSizeInBytes))
            setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
            setDefaultRequestOptions(requestOptions())
            setLogLevel(Log.DEBUG)
        }
    }


    private fun requestOptions(): RequestOptions {
        return RequestOptions()
            .signature(
                ObjectKey(
                    System.currentTimeMillis() / (24 * 60 * 60 * 1000)
                )
            )
            .encodeFormat(Bitmap.CompressFormat.PNG)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(false)
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    companion object{
        private const val GLIDE_REQUEST_TIMEOUT_SECONDS = 60L
        private const val DISK_CACHE_SIZE_MB = 250
    }
}