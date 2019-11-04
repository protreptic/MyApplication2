package example.myapplication.presentation.user

import android.os.Bundle
import android.os.Bundle.EMPTY
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.widget.editorActionEvents
import example.myapplication.R
import example.myapplication.auxiliary.extensions.dismissKeyboard
import example.myapplication.auxiliary.extensions.plusAssign
import example.myapplication.auxiliary.extensions.visibleOrGone
import example.myapplication.data.model.GitHubUser
import example.myapplication.domain.user.GitHubUserInteractor
import example.myapplication.presentation.MainRouter
import example.myapplication.presentation.abs.AbsFragment
import kotlinx.android.synthetic.main.view__github_user.*
import kotlinx.android.synthetic.main.view__view_error.*
import kotlinx.android.synthetic.main.view__view_loading.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class GitHubUserFragment: AbsFragment(), GitHubUserView {

    companion object {

        const val FRAGMENT_TAG = "fragment_tag__github_user"

        fun newInstance(): Fragment = GitHubUserFragment().apply {
            arguments = EMPTY
        }

    }

    override val layout: Int = R.layout.view__github_user

    @Inject lateinit var gitHubUserInteractor: GitHubUserInteractor
    @InjectPresenter lateinit var presenter: GitHubUserPresenter

    @ProvidePresenter
    fun providePresenter(): GitHubUserPresenter =
            GitHubUserPresenter(gitHubUserInteractor,
                MainRouter(fragmentManager), schedulers)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables +=
            searchField.editorActionEvents { event -> event.actionId == IME_ACTION_SEARCH }
                .map { event -> event.view.text.toString() }
                .filter { text -> text.isNotEmpty() }
                .doOnNext { query ->
                    presenter.search(query)

                    searchField.dismissKeyboard()
                }
                .subscribe()
    }

    override fun showGitHubUser(gitHubUser: GitHubUser) {
        Glide.with(this)
            .load(gitHubUser.avatar)
            .centerCrop()
            .into(userAvatar)

        userLogin.text = gitHubUser.login
        userDescription.text =
            getString(R.string.userDescription,
                gitHubUser.repositories, gitHubUser.followers,
                    gitHubUser.stargazers)
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