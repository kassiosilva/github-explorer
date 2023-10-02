package com.example.githubexplorer.data

import com.example.githubexplorer.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSource {
    fun findUser(callback: UserCallback, username: String) {
        HTTPClient.retrofit()
            .create(GithubAPI::class.java)
            .findUser(username)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        val user = response.body()

                        callback.onSuccess(user)
                    } else {
                        val error = response.errorBody()?.string()

                        callback.onError(error ?: "Erro desconhecido")
                    }

                    callback.onComplete()
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }
            })
    }
}