package com.primechord.stubwebserver.application

import android.app.Application
import androidx.annotation.UiThread
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.primechord.stubwebserver.services.GitHubService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@UiThread
class AppCompositionRoot(val application: Application, baseUrlProvider: BaseUrlProvider) {

    private val retrofit: Retrofit by lazy {
        val mediaType = "application/json".toMediaType()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(mediaType))
            .baseUrl(baseUrlProvider.getUrl())
            .build()
    }

    val gitHubService: GitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }
}
