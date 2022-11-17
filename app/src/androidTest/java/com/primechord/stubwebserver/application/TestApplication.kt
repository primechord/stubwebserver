package com.primechord.stubwebserver.application

class TestApplication : MyApplication() {

    override fun createAppCompositionRoot(): AppCompositionRoot {
        return AppCompositionRoot(this, BaseUrlProviderImpl())
    }
}

class BaseUrlProviderImpl : BaseUrlProvider {
    override fun getUrl() = "http:/localhost:8080"
}
