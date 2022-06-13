package com.example.githubrepositories.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.model.Pull
import com.example.githubrepositories.repository.PullsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullViewModel @Inject constructor(
    private val repository: PullsRepository
) : ViewModel() {

    private val _pulls = MutableLiveData<List<Pull>>()
    val pulls: LiveData<List<Pull>> = _pulls

    fun getPullsList(fullName: String) {
        viewModelScope.launch {
            val returnedPulls =
                repository.getPulls(
                    fullName = fullName
                )
            returnedPulls.let {
                _pulls.value = it
            }
        }
    }
}