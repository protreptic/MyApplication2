package example.myapplication.data.repository.user

import example.myapplication.data.model.GitHubUser
import example.myapplication.data.repository.user.source.GitHubUserDataSource
import example.myapplication.domain.user.GitHubUserRepository
import io.reactivex.Single
import javax.inject.Inject

class GitHubUserRepositoryImpl
    @Inject constructor(
        private val cloud: GitHubUserDataSource,
        private val cache: GitHubUserDataSource):
            GitHubUserRepository {

    override fun user(): Single<GitHubUser> =
            cloud.user()
                 .flatMap(::repositories)
                 .flatMap(cache::retain)

    private fun repositories(user: GitHubUser): Single<GitHubUser> =
            cloud.repositories()
                .map { repositories -> user.apply {
                    this.repositories = repositories.size
                    this.stargazers =
                        repositories.sumBy {
                            repository ->
                                repository.stargazers ?: 0
                        }
                    }
                }

}