package com.example.githubrepositories.service

import com.example.githubrepositories.model.Owner
import com.example.githubrepositories.model.PullResponse
import com.example.githubrepositories.model.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubAPI {

    @GET("/users")
    suspend fun getUsers(): Response<Owner>

    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Response<RepositoryResponse>

    @GET("/repos/{fullName}/pulls")
    fun getPullRequestsList(
        @Query("fullName") fullName: String
    ): Response<PullResponse>
}