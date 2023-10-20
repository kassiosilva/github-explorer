package com.example.githubexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.githubexplorer.model.RepositoryResponse
import com.example.githubexplorer.presentation.MainPresenter

class MainActivity : AppCompatActivity() {
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        val editSearchRepo = findViewById<EditText>(R.id.edit_search_repo)
        val buttonSearch = findViewById<ImageButton>(R.id.button_search_repo)

        buttonSearch.setOnClickListener {
            presenter.repoNameIsValid(editSearchRepo.text.toString())
        }
    }

    fun errorSearch(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun addRepository(repository: RepositoryResponse) {
        Toast.makeText(this, "Repositório => ${repository.fullName}", Toast.LENGTH_SHORT).show()
    }
}