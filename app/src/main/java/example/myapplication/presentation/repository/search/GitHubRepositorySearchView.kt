package example.myapplication.presentation.repository.search

import example.myapplication.data.model.GitHubRepository
import example.myapplication.presentation.abs.AbsView

interface GitHubRepositorySearchView: AbsView {

    /**
     * Показывает список найденных github-репозиториев.
     *
     * @param gitHubRepositories github-репозитории
     */
    fun showGitHubRepositories(gitHubRepositories: List<GitHubRepository>)

    /**
     * Показывает сообщение о пустом
     * результате поиска.
     */
    fun showNothingFound()

}