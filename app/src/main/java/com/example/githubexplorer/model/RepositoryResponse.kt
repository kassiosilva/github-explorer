package com.example.githubexplorer.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("full_name") val fullName: String,
    val owner: OwnerResponse,
    val description: String
)
