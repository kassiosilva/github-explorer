package com.example.githubexplorer.data

import com.example.githubexplorer.model.RepositoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRemoteDataSource {
    fun getRepository(callback: MainCallback, repositoryName: String) {
        HTTPClient.retrofit()
            .create(GithubAPI::class.java)
            .getRepository(repositoryName)
            .enqueue(object : Callback<RepositoryResponse>{
                override fun onResponse(
                    call: Call<RepositoryResponse>,
                    response: Response<RepositoryResponse>
                ) {
                    if (response.isSuccessful) {
                        val repository = response.body()

                        callback.onSuccess(repository)
                    } else {
                        val error = response.errorBody()?.string()

                        callback.onError(error ?: "Erro desconhecido")
                    }

                    callback.onComplete()
                }

                override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }
            })
    }
}