package com.example.githubexplorer.presentation

import com.example.githubexplorer.MainActivity
import com.example.githubexplorer.data.MainCallback
import com.example.githubexplorer.data.MainRemoteDataSource
import com.example.githubexplorer.model.RepositoryResponse

class MainPresenter(
    private val view: MainActivity,
    private val dataSource: MainRemoteDataSource = MainRemoteDataSource()
) : MainCallback {
    fun repoNameIsValid(repositoryName: String) {
        if (repositoryName.trim().isEmpty()) {
            view.errorSearch("usuário/repositório inválido.")
            return
        }

        view.showProgress()

        dataSource.getRepository(this, repositoryName)
    }

    override fun onSuccess(repository: RepositoryResponse?) {
        if (repository !=  null) {
            view.addRepository(repository)
            return
        }

        view.errorSearch("Ocorreu um erro, tente novamente mais tarde.")
    }

    override fun onError(message: String) {
        view.errorSearch(message)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}