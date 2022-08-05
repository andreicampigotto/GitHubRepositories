package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
//    @SerializedName("id")
//    val id: Long,
    @SerializedName("login")
    val login: String?,
    @SerializedName("avatar_url")
    val avatar_url: String?,
//    @SerializedName("name")
//    val name: String,
//    @SerializedName("html_url")
//    val html_url: String,
//    @SerializedName("bio")
//    val bio: String,
) : Serializable