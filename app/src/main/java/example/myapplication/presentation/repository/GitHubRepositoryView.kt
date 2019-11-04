package example.myapplication.presentation.repository

import example.myapplication.data.model.GitHubRepository
import example.myapplication.presentation.abs.AbsView

interface GitHubRepositoryView: AbsView {

    /**
     * Показывает github-репозиторий.
     *
     * @param gitHubRepository github-репозиторий
     */
    fun showGitHubRepository(gitHubRepository: GitHubRepository)

}