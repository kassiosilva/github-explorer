package com.example.githubexplorer.data

import android.os.Handler
import android.os.Looper
import com.example.githubexplorer.model.User

class UserRemoteDataSource {
    fun findUser(callback: UserCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val user = User("Kássio Silva", "https://avatars.githubusercontent.com/u/42787648?v=4")

            callback.onSuccess(user)
            callback.onComplete()
        }, 3000)
    }
}