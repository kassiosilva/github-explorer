package com.example.githubexplorer.data

import com.example.githubexplorer.model.User

interface UserCallback {
    fun onSuccess(user: User)
    fun onError ()
    fun onComplete()
}