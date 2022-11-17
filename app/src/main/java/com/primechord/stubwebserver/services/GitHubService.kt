package com.primechord.stubwebserver.services

import com.primechord.stubwebserver.prod_models.RepoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<RepoItem>>
}
