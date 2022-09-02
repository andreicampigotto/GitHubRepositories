package com.example.githubrepositories.repository

import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.service.GitHubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(
    private val service: GitHubAPI,
) {
    suspend fun getRepositories(language: String, sort: String, page: Int): List<Repository>? {
        val resultAPI = withContext(Dispatchers.Default) {
            val response = service.getRepositories(
                q = "language:$language",
                sort = sort,
                page = page
            )
            val processedResponse = processData(response)
            processedResponse?.items
        }
        return resultAPI

    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }
}