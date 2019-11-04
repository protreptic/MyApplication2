package example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class GitHubRepository(
    val id: Long,
    val name: String,
    val owner: GitHubUser,
    @SerializedName("forks_count")
    val forks: Int? = 0,
    @SerializedName("watchers_count")
    val watchers: Int? = 0,
    @SerializedName("stargazers_count")
    val stargazers: Int? = 0)