package com.example.githubexplorer.data

import retrofit2.Call
import retrofit2.http.GET

interface GithubAPI {
    @GET("users")
    fun findUser() : Call<List<String>>
}