package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("html_url")
    val html_url: String,
    @SerializedName("bio")
    val bio: String,
)