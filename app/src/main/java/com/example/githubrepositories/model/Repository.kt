package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repository(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("stargazers_count")
    val stargazers_count: Long,
    @SerializedName("watchers_count")
    val watchers_count: Long,
    @SerializedName("forks_count")
    val forks_count: Long,
): Serializable

data class RepositoryResponse(
    val items: List<Repository>
)