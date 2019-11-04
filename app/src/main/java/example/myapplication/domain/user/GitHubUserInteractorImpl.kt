package example.myapplication.domain.user

import example.myapplication.data.model.GitHubUser
import io.reactivex.Single
import javax.inject.Inject

class GitHubUserInteractorImpl
    @Inject constructor(
        private val gitHubUserRepository: GitHubUserRepository):
            GitHubUserInteractor {

    override fun user(): Single<GitHubUser> = gitHubUserRepository.user()

}