package example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class GitHubRepositorySearchResult(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<GitHubRepository>)