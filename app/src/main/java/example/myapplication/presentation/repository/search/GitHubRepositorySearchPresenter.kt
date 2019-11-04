package example.myapplication.presentation.repository.search

import example.myapplication.auxiliary.rx.Schedulers
import example.myapplication.domain.repository.GitHubRepositoryInteractor
import example.myapplication.auxiliary.extensions.plusAssign
import example.myapplication.data.model.GitHubRepository
import example.myapplication.presentation.MainRouter
import example.myapplication.presentation.abs.AbsPresenter

class GitHubRepositorySearchPresenter(
    private val query: String,
    private val gitHubRepositoryInteractor: GitHubRepositoryInteractor,
    private val mainRouter: MainRouter,
    private val schedulers: Schedulers):
        AbsPresenter<GitHubRepositorySearchView>() {

    override fun onFirstViewAttach() {
        attachedView.showLoading()

        disposables +=
            gitHubRepositoryInteractor
                .search(query)
                .observeOn(schedulers.ui())
                .doOnSuccess(::onGitHubRepositorySearchSuccess)
                .doOnError(attachedView::showError)
                .subscribeOn(schedulers.background())
                .subscribe()
    }

    private fun onGitHubRepositorySearchSuccess(gitHubRepositories: List<GitHubRepository>) {
        if (gitHubRepositories.isNotEmpty()) {
            attachedView.showGitHubRepositories(gitHubRepositories)
            attachedView.showContent()
        } else {
            attachedView.showNothingFound()

            mainRouter.back()
        }
    }

    fun displayRepository(gitHubRepository: GitHubRepository) {
        mainRouter.toGitHubRepository(gitHubRepository)
    }

}