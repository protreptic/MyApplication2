package example.myapplication.domain.repository

import example.myapplication.data.model.GitHubRepository
import io.reactivex.Maybe
import io.reactivex.Single

interface GitHubRepositoryInteractor {

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

}