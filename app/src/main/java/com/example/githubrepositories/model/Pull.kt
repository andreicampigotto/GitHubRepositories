package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName

data class Pull(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("created_at")
    val owner: Owner
)

data class PullResponse(
    val items: List<Pull>
)