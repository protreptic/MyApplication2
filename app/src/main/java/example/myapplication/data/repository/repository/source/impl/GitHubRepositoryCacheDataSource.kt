package example.myapplication.data.repository.repository.source.impl

import example.myapplication.data.model.GitHubRepository
import example.myapplication.data.repository.repository.source.GitHubRepositoryDataSource
import io.reactivex.Maybe
import io.reactivex.Single

class GitHubRepositoryCacheDataSource: GitHubRepositoryDataSource {

    private val cache: MutableList<GitHubRepository> = mutableListOf()

    override fun fetchById(gitHubRepositoryId: Long): Maybe<GitHubRepository> =
        Maybe.defer {
            val foundRepository =
                    cache.find { repository -> repository.id == gitHubRepositoryId }

            if (foundRepository != null) {
                Maybe.just(foundRepository)
            } else {
                Maybe.empty()
            }
        }

    override fun search(query: String): Single<List<GitHubRepository>> =
        Single.just(cache)

    override fun retain(gitHubRepositories: List<GitHubRepository>): Single<List<GitHubRepository>> =
        Single.fromCallable {
            cache.clear()
            cache += gitHubRepositories
            cache
        }

}