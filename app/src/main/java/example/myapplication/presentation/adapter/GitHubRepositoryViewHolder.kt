package example.myapplication.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import example.myapplication.data.model.GitHubRepository
import example.myapplication.presentation.adapter.GitHubRepositoryAdapter.*
import kotlinx.android.synthetic.main.view__github_repository_search__repository.view.*

class GitHubRepositoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(gitHubRepository: GitHubRepository, delegate: Delegate?) {
        itemView.apply {
            Glide.with(this)
                .load(gitHubRepository.owner.avatar)
                .centerCrop()
                .into(userAvatar)

            repositoryName.text = gitHubRepository.name
            repositoryDescription.text = gitHubRepository.owner.login

            setOnClickListener {
                delegate?.onGitHubRepositoryPicked(gitHubRepository)
            }
        }
    }

}