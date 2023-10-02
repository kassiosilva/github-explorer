package com.example.githubexplorer.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val name: String,
    @SerializedName("avatar_url") val avatarUrl: String,
)
