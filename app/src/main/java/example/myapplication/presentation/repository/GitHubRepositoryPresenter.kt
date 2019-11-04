package example.myapplication.presentation.repository

import example.myapplication.auxiliary.extensions.plusAssign
import example.myapplication.auxiliary.rx.Schedulers
import example.myapplication.data.model.GitHubRepository
import example.myapplication.domain.repository.GitHubRepositoryInteractor
import example.myapplication.presentation.abs.AbsPresenter

class GitHubRepositoryPresenter(
    private val gitHubRepositoryId: Long,
    private val gitHubRepositoryInteractor: GitHubRepositoryInteractor,
    private val schedulers: Schedulers):
        AbsPresenter<GitHubRepositoryView>() {

    override fun onFirstViewAttach() {
        attachedView.showLoading()

        disposables +=
            gitHubRepositoryInteractor
                .fetchById(gitHubRepositoryId)
                .observeOn(schedulers.ui())
                .doOnSuccess(::onGitHubRepositorySuccess)
                .doOnError(attachedView::showError)
                .subscribeOn(schedulers.background())
                .subscribe()
    }

    private fun onGitHubRepositorySuccess(gitHubRepository: GitHubRepository) {
        attachedView.showGitHubRepository(gitHubRepository)
        attachedView.showContent()
    }

}