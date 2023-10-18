package com.example.githubexplorer.data

import com.example.githubexplorer.model.UserResponse

interface UserCallback {
    fun onSuccess(user: UserResponse?)
    fun onError (message: String)
    fun onComplete()
}