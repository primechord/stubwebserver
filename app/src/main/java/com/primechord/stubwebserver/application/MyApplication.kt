package com.primechord.stubwebserver.application

import android.app.Application

open class MyApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = createAppCompositionRoot()
        super.onCreate()
    }

    open fun createAppCompositionRoot() = AppCompositionRoot(this, BaseUrlProviderImpl())
}

class BaseUrlProviderImpl : BaseUrlProvider {
    override fun getUrl() = "https://api.github.com"
}
