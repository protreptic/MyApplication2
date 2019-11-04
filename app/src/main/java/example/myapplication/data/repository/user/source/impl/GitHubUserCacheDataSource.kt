package example.myapplication.data.repository.user.source.impl

import example.myapplication.data.model.GitHubRepository
import example.myapplication.data.model.GitHubUser
import example.myapplication.data.repository.user.source.GitHubUserDataSource
import io.reactivex.Maybe
import io.reactivex.Single

class GitHubUserCacheDataSource: GitHubUserDataSource {

    private var cache: GitHubUser? = null

    override fun user(): Single<GitHubUser> =
            Single.defer {
                if (cache != null) {
                    Single.just(cache)
                } else {
                    Maybe.empty<GitHubUser>()
                         .toSingle()
                }
            }

    override fun repositories(): Single<List<GitHubRepository>> = Single.never()

    override fun retain(gitHubUser: GitHubUser): Single<GitHubUser> =
            Single.fromCallable {
                cache = gitHubUser
                cache
            }

}