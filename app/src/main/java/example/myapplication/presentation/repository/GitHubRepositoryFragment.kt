package example.myapplication.presentation.repository

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import example.myapplication.R
import example.myapplication.auxiliary.extensions.visibleOrGone
import example.myapplication.data.model.GitHubRepository
import example.myapplication.domain.repository.GitHubRepositoryInteractor
import example.myapplication.presentation.abs.AbsFragment
import kotlinx.android.synthetic.main.view__github_repository.*
import kotlinx.android.synthetic.main.view__view_error.*
import kotlinx.android.synthetic.main.view__view_loading.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class GitHubRepositoryFragment: AbsFragment(), GitHubRepositoryView {

    companion object {

        const val FRAGMENT_TAG = "fragment_tag__github_repository"
        const val FRAGMENT_ARG__GITHUB_REPOSITORY_ID = "fragment_arg__github_repository_id"

        fun newInstance(repositoryId: Long): Fragment = GitHubRepositoryFragment().apply {
            arguments = Bundle().apply {
                putLong(FRAGMENT_ARG__GITHUB_REPOSITORY_ID, repositoryId)
            }
        }

    }

    private val gitHubRepositoryId: Long by lazy {
        arguments?.getLong(FRAGMENT_ARG__GITHUB_REPOSITORY_ID) ?: 0L
    }

    override val layout: Int = R.layout.view__github_repository

    @Inject lateinit var gitHubRepositoryInteractor: GitHubRepositoryInteractor
    @InjectPresenter lateinit var presenter: GitHubRepositoryPresenter

    @ProvidePresenter
    fun providePresenter() =
            GitHubRepositoryPresenter(
                gitHubRepositoryId, gitHubRepositoryInteractor,
                    schedulers)

    override fun showGitHubRepository(gitHubRepository: GitHubRepository) {
        Glide.with(this)
            .load(gitHubRepository.owner.avatar)
            .centerCrop()
            .into(userAvatar)

        repositoryLogin.text = gitHubRepository.owner.login
        repositoryDescription.text =
            getString(R.string.repositoryDescription,
                gitHubRepository.forks, gitHubRepository.watchers,
                    gitHubRepository.stargazers)
    }

    override fun showContent() {
        view_error?.visibleOrGone(false)
        view_loading?.visibleOrGone(false)
    }

    override fun showLoading() {
        view_error?.visibleOrGone(false)
        view_loading?.visibleOrGone(true)
    }

    override fun showError(error: Any?) {
        view_error?.visibleOrGone(true)
        view_error_message?.text = error?.toString()

        view_loading?.visibleOrGone(false)
    }

}