package com.example.githubrepositories.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.githubrepositories.repository.PullsRepository
import com.example.githubrepositories.repository.RepositoriesRepository
import com.example.githubrepositories.service.GitHubAPI

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providerGitHub(retrofit: Retrofit): GitHubAPI = retrofit.create(GitHubAPI::class.java)

    @Provides
    fun provideGitHubRepository(service: GitHubAPI): RepositoriesRepository =
        RepositoriesRepository(service)

    @Provides
    fun provideGitHubPull(service: GitHubAPI): PullsRepository =
        PullsRepository(service)
}