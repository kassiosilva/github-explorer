package com.example.githubexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubexplorer.model.RepositoryResponse
import com.example.githubexplorer.presentation.MainPresenter

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerRepos: RecyclerView
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: RepositoryAdapter

    private val repositories = mutableListOf<RepositoryResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        progressBar = findViewById(R.id.progress_bar_repos)

        adapter = RepositoryAdapter(repositories)

        recyclerRepos = findViewById(R.id.recycler_repos)
        recyclerRepos.adapter = adapter
        recyclerRepos.layoutManager = LinearLayoutManager(this)

        val editSearchRepo = findViewById<EditText>(R.id.edit_search_repo)
        val buttonSearch = findViewById<ImageButton>(R.id.button_search_repo)

        buttonSearch.setOnClickListener {
            presenter.repoNameIsValid(editSearchRepo.text.toString())
        }

        editSearchRepo.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    presenter.repoNameIsValid(editSearchRepo.text.toString())
                    true
                }

                else -> false
            }
        }
    }

    fun errorSearch(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun addRepository(repository: RepositoryResponse) {
        repositories.add(repository)
        adapter.notifyDataSetChanged()
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
        recyclerRepos.visibility = View.GONE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
        recyclerRepos.visibility = View.VISIBLE
    }

    private inner class RepositoryAdapter(
        private val repositories: List<RepositoryResponse>
    ) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
            val view = layoutInflater.inflate(R.layout.item_repo, parent, false)

            return RepositoryViewHolder(view)
        }

        override fun getItemCount(): Int = repositories.size

        override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
            val currentItem = repositories[position]
            holder.bind(currentItem)
        }

        inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: RepositoryResponse) {
                val textRepo: TextView = itemView.findViewById(R.id.text_repo)
                textRepo.text = item.fullName

                val textDesc: TextView = itemView.findViewById(R.id.text_desc)
                textDesc.text = item.description
            }
        }

    }
}