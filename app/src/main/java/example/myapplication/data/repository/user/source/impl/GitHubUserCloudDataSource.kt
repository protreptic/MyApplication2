package example.myapplication.data.repository.user.source.impl

import example.myapplication.data.model.GitHubRepository
import example.myapplication.data.model.GitHubUser
import example.myapplication.data.GitHub
import example.myapplication.data.repository.user.source.GitHubUserDataSource
import io.reactivex.Single
import javax.inject.Inject

class GitHubUserCloudDataSource
    @Inject constructor(
        private val gitHub: GitHub):
            GitHubUserDataSource {

    override fun user(): Single<GitHubUser> = gitHub.user()

    override fun repositories(): Single<List<GitHubRepository>> = gitHub.repositories()

    override fun retain(gitHubUser: GitHubUser): Single<GitHubUser> = Single.never()

}