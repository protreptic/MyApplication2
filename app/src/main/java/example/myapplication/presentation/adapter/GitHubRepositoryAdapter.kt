package example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.myapplication.R
import example.myapplication.data.model.GitHubRepository

class GitHubRepositoryAdapter(
    private var data: List<GitHubRepository> = listOf(),
    private val delegate: Delegate? = null):
        RecyclerView.Adapter<GitHubRepositoryViewHolder>() {

    interface Delegate {

        /**
         * Событие происходит при выборе пользователем
         * github-репозитория из списка.
         *
         * @param gitHubRepository выбранный github-репозиторий
         */
        fun onGitHubRepositoryPicked(gitHubRepository: GitHubRepository)

    }

    fun updateData(newData: List<GitHubRepository>) {
        data = newData

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepositoryViewHolder =
        GitHubRepositoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view__github_repository_search__repository, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GitHubRepositoryViewHolder, position: Int) {
        val gitHubRepository = data[position]

        holder.bind(gitHubRepository, delegate)
    }

}