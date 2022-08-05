package com.example.githubrepositories.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pull(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("user")
    val user: User
): Serializable {
    fun formattedDate(): String {
        val dateWithoutHours = created_at.split("T")[0].split("-")

        return "${dateWithoutHours[2]}/${dateWithoutHours[1]}/${dateWithoutHours[0]}"
    }
}