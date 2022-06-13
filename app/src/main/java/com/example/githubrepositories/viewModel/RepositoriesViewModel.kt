package com.example.githubrepositories.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.repository.RepositoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repository: RepositoriesRepository
) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> = _repositories

    private val _page = MutableLiveData<Int>()
    val page: LiveData<Int> = _page

    fun getRepositoryList(page: Int) {
        viewModelScope.launch {
            val returnedRepositories =
                repository.getRepositories(
                    language = "Kotlin",
                    sort = "stars",
                    page = page
                )
            returnedRepositories.let {
                _repositories.value = it
            }
        }
    }

    fun getRepositoryListByName(language: String, page: Int) {
        viewModelScope.launch {
            val returnedRepositories =
                repository.getRepositories(
                    language = language,
                    sort = "stars",
                    page = page
                )
            returnedRepositories.let {
                _repositories.value = it
            }
        }
    }

    fun nextPage() {
        _page.value = (_page.value ?: 1) + 1
    }
}