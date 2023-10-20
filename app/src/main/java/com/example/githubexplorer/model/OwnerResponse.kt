package com.example.githubexplorer.model

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("avatar_url") val avatarUrl: String
)