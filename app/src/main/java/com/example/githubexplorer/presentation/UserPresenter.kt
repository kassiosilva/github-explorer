package com.example.githubexplorer.presentation

import com.example.githubexplorer.UserActivity
import com.example.githubexplorer.data.UserCallback
import com.example.githubexplorer.data.UserRemoteDataSource
import com.example.githubexplorer.model.User

class UserPresenter(
    private val view: UserActivity,
    private val dataSource: UserRemoteDataSource = UserRemoteDataSource()
) : UserCallback {
    fun isUserValid(userName: String) {
        if (userName.isEmpty()) {
            view.userNotValid("Digite um usuário válido")
            return;
        }

        view.showProgress()

        dataSource.findUser(this)
    }

    override fun onSuccess(user: User) {
        view.redirectSuccess(user)
    }

    override fun onError() {
        // Erros
    }

    override fun onComplete() {
        view.hideProgress()
    }
}