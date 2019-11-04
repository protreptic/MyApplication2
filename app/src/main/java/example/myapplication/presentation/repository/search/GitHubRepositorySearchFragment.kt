package example.myapplication.presentation.repository.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import example.myapplication.R
import example.myapplication.auxiliary.extensions.visibleOrGone
import example.myapplication.data.model.GitHubRepository
import example.myapplication.domain.repository.GitHubRepositoryInteractor
import example.myapplication.presentation.MainRouter
import example.myapplication.presentation.abs.AbsFragment
import example.myapplication.presentation.adapter.GitHubRepositoryAdapter
import kotlinx.android.synthetic.main.view__github_repository_search.*
import kotlinx.android.synthetic.main.view__view_error.*
import kotlinx.android.synthetic.main.view__view_loading.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class GitHubRepositorySearchFragment: AbsFragment(), GitHubRepositorySearchView, GitHubRepositoryAdapter.Delegate {

    companion object {

        const val FRAGMENT_TAG = "fragment_tag__github_repository_search"
        const val FRAGMENT_ARG_QUERY = "fragment_arg__query"

        fun newInstance(query: String): Fragment = GitHubRepositorySearchFragment().apply {
            arguments = Bundle().apply {
                putString(FRAGMENT_ARG_QUERY, query)
            }
        }

    }

    private val query: String by lazy {
        arguments?.getString(FRAGMENT_ARG_QUERY) ?: ""
    }

    override val layout: Int = R.layout.view__github_repository_search

    @Inject lateinit var gitHubRepositoryInteractor: GitHubRepositoryInteractor
    @InjectPresenter lateinit var presenter: GitHubRepositorySearchPresenter

    @ProvidePresenter
    fun providePresenter() =
            GitHubRepositorySearchPresenter(
                query, gitHubRepositoryInteractor,
                    MainRouter(fragmentManager), schedulers)

    private val gitHubRepositoryAdapter = GitHubRepositoryAdapter(delegate = this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRepositories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = gitHubRepositoryAdapter
        }
    }

    override fun showGitHubRepositories(gitHubRepositories: List<GitHubRepository>) {
        gitHubRepositoryAdapter
            .updateData(gitHubRepositories)
    }

    override fun showNothingFound() {
        Toast.makeText(context, getString(R.string.nothing_found), Toast.LENGTH_LONG).show()
    }

    override fun onGitHubRepositoryPicked(gitHubRepository: GitHubRepository) {
        presenter.displayRepository(gitHubRepository)
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