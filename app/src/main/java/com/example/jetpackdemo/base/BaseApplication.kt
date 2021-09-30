package com.example.jetpackdemo.base

import android.app.Application
import com.example.jetpackdemo.helper.glide.ImageLoader

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ImageLoader.getDefault().diskCacheOptions()
            .setDiskCacheDirPath(getExternalFilesDir("Cache")?.path ?: filesDir.path)
            .setDiskCacheFolderName("Image")
            .setDiskCacheSize(2 * 1024 * 1024) // 设置磁盘缓存2G
            .setBitmapPoolSize(2.0f)
            .setMemoryCacheSize(1.5f)
            .build()
    }
}
