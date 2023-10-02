package com.example.githubexplorer.data

import com.example.githubexplorer.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("users/{username}")
    fun findUser(@Path("username") username: String) : Call<UserResponse>
}