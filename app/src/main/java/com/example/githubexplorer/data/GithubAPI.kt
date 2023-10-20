package com.example.githubexplorer.data

import com.example.githubexplorer.model.RepositoryResponse
import com.example.githubexplorer.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("users/{username}")
    fun findUser(@Path("username") username: String): Call<UserResponse>

    @GET("repos/{repositoryName}")
    fun getRepository(
        @Path(
            value = "repositoryName",
            encoded = true
        ) repositoryName: String
    ): Call<RepositoryResponse>
}