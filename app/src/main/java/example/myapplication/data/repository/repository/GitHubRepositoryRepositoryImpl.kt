package example.myapplication.data.repository.repository

import example.myapplication.data.model.GitHubRepository
import example.myapplication.data.repository.repository.source.GitHubRepositoryDataSource
import example.myapplication.domain.repository.GitHubRepositoryRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepositoryRepositoryImpl
    @Inject constructor(
        private val cloud: GitHubRepositoryDataSource,
        private val cache: GitHubRepositoryDataSource):
            GitHubRepositoryRepository {

    override fun fetchById(gitHubRepositoryId: Long): Maybe<GitHubRepository> =
            cache.fetchById(gitHubRepositoryId)

    override fun search(query: String): Single<List<GitHubRepository>> =
            cloud.search(query)
                 .flatMap(cache::retain)

}