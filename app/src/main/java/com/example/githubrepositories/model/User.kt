package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val username: String,

    @SerializedName("avatar_url")
    val avatarURL: String
)