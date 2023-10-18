package com.example.githubexplorer.presentation

import com.example.githubexplorer.UserActivity
import com.example.githubexplorer.data.UserCallback
import com.example.githubexplorer.data.UserRemoteDataSource
import com.example.githubexplorer.model.UserResponse

class UserPresenter(
    private val view: UserActivity,
    private val dataSource: UserRemoteDataSource = UserRemoteDataSource()
) : UserCallback {
    fun isUserValid(username: String) {
        if (username.isEmpty()) {
            view.userNotValid("Digite um usuário válido")
            return
        }

        view.showProgress()

        dataSource.findUser(this, username)
    }

    override fun onSuccess(user: UserResponse?) {
        if (user != null) {
            view.redirectSuccess(user)
            return
        }

        view.userNotValid("Ocorreu um erro. Tento novamente mais tarde")
    }

    override fun onError(message: String) {
        view.userNotValid(message)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}