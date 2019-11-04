package example.myapplication.domain.repository

import example.myapplication.data.model.GitHubRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepositoryInteractorImpl
    @Inject constructor(
        private val gitHubRepositoryRepository: GitHubRepositoryRepository):
            GitHubRepositoryInteractor {

    override fun fetchById(gitHubRepositoryId: Long): Maybe<GitHubRepository> =
            gitHubRepositoryRepository
                .fetchById(gitHubRepositoryId)

    override fun search(query: String): Single<List<GitHubRepository>> =
            gitHubRepositoryRepository
                .search(query)

}