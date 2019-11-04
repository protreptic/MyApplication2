package example.myapplication.presentation.user

import example.myapplication.data.model.GitHubUser
import example.myapplication.presentation.abs.AbsView

interface GitHubUserView: AbsView {

    /**
     * Показывает github-пользователя.
     *
     * @param gitHubUser github-пользователь
     */
    fun showGitHubUser(gitHubUser: GitHubUser)

}