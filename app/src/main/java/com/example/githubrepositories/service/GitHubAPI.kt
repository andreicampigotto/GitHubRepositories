package com.example.githubrepositories.service

import com.example.githubrepositories.model.Pull
import com.example.githubrepositories.model.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {

    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int = 1
    ): Response<RepositoryResponse>

    @GET("/repos/{fullName}/pulls")
    suspend fun getPullRequestsList(
        @Path(
            "fullName",
            encoded = true
        ) fullName: String
    ): Response<List<Pull>>
}