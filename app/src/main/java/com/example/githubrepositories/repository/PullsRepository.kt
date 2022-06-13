package com.example.githubrepositories.repository

import com.example.githubrepositories.model.Pull
import com.example.githubrepositories.service.GitHubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PullsRepository @Inject constructor(
    private val service: GitHubAPI
) {

    suspend fun getPulls(fullName: String): List<Pull>? {
        val resultAPI = withContext(Dispatchers.Default) {
            val response = service.getPullRequestsList(
                fullName = fullName
            )
            val processResponse = processData(response)
            processResponse?.items
        }
        return resultAPI
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }
}