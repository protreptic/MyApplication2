package example.myapplication.presentation.user

import example.myapplication.auxiliary.extensions.plusAssign
import example.myapplication.auxiliary.rx.Schedulers
import example.myapplication.data.model.GitHubUser
import example.myapplication.domain.user.GitHubUserInteractor
import example.myapplication.presentation.MainRouter
import example.myapplication.presentation.abs.AbsPresenter

class GitHubUserPresenter(
    private val gitHubUserInteractor: GitHubUserInteractor,
    private val mainRouter: MainRouter,
    private val schedulers: Schedulers):
        AbsPresenter<GitHubUserView>() {

    override fun onFirstViewAttach() {
        attachedView.showLoading()

        disposables +=
            gitHubUserInteractor.user()
                .observeOn(schedulers.ui())
                .doOnSuccess(::onUserSuccess)
                .doOnError(attachedView::showError)
                .subscribeOn(schedulers.background())
                .subscribe()
    }

    private fun onUserSuccess(gitHubUser: GitHubUser) {
        attachedView.showGitHubUser(gitHubUser)
        attachedView.showContent()
    }

    fun search(query: String) {
        mainRouter.toGitHubRepositorySearch(query)
    }

}