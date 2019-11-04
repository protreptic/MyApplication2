package example.myapplication.data

import example.myapplication.data.model.GitHubRepository
import example.myapplication.data.model.GitHubRepositorySearchResult
import example.myapplication.data.model.GitHubUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHub {

    @GET("/user")
    fun user(): Single<GitHubUser>

    @GET("/user/repos")
    fun repositories(): Single<List<GitHubRepository>>

    @GET("/search/repositories?sort=stars&order=desc")
    fun search(@Query("q") query: String): Single<GitHubRepositorySearchResult>

 }