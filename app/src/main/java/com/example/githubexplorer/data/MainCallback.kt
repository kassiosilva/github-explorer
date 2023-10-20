package com.example.githubexplorer.data

import com.example.githubexplorer.model.RepositoryResponse

interface MainCallback {
    fun onSuccess(repository: RepositoryResponse?)
    fun onError (message: String)
    fun onComplete()
}