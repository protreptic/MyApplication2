package example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String,
    val followers: Int) {

    var repositories: Int = 0
    var stargazers: Int = 0

}