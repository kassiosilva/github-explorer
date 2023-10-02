package com.example.githubexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.githubexplorer.data.UserRemoteDataSource
import com.example.githubexplorer.model.User
import com.example.githubexplorer.model.UserResponse
import com.example.githubexplorer.presentation.UserPresenter

class UserActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var editUser: EditText

    private lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        presenter = UserPresenter(this)

        progressBar = findViewById(R.id.progress_bar)
        editUser = findViewById(R.id.edit_user)
        val buttonSearchUser = findViewById<Button>(R.id.button_search_user)

        buttonSearchUser.setOnClickListener {
            presenter.isUserValid(editUser.text.toString())
        }
    }

    fun userNotValid(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun redirectSuccess(user: UserResponse) {
        Toast.makeText(this, "Seja bem-vindo, ${user.avatarUrl}", Toast.LENGTH_SHORT).show()
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
        editUser.visibility = View.GONE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
        editUser.visibility = View.VISIBLE
    }
}