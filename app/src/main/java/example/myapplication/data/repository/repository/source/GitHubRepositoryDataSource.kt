package example.myapplication.data.repository.repository.source

import example.myapplication.data.model.GitHubRepository
import io.reactivex.Maybe
import io.reactivex.Single

interface GitHubRepositoryDataSource {

    /**
     * Возвращает github-репозиторий по идентификатору.
     *
     * @param gitHubRepositoryId идентификатор github-репозитория
     * @return github-репозиторий
     */
    fun fetchById(gitHubRepositoryId: Long): Maybe<GitHubRepository>

    /**
     * Ищет github-репозитории по запросу.
     *
     * @param query поисковый запрос
     * @return результаты поиска
     */
    fun search(query: String): Single<List<GitHubRepository>>

    /**
     * Запоминает github-репозитории.
     *
     * @param gitHubRepositories github-репозитории
     * @return результаты поиска
     */
    fun retain(gitHubRepositories: List<GitHubRepository>): Single<List<GitHubRepository>>

}