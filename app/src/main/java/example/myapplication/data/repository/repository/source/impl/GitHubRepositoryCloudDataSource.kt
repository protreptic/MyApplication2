package example.myapplication.data.repository.repository.source.impl

import example.myapplication.data.model.GitHubRepository
import example.myapplication.data.GitHub
import example.myapplication.data.repository.repository.source.GitHubRepositoryDataSource
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepositoryCloudDataSource
    @Inject constructor(
        private val gitHub: GitHub):
            GitHubRepositoryDataSource {

    override fun fetchById(gitHubRepositoryId: Long): Maybe<GitHubRepository> =
            Maybe.empty()

    override fun search(query: String): Single<List<GitHubRepository>> =
            gitHub.search(query)
                  .map { result -> result.items }

    override fun retain(gitHubRepositories: List<GitHubRepository>): Single<List<GitHubRepository>> =
            Single.never()

}