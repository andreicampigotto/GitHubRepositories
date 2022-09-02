package com.example.githubrepositories.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.R
import com.example.githubrepositories.adapter.RepositoriesAdapter
import com.example.githubrepositories.databinding.RepositoriesFragmentBinding
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.viewModel.RepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment() : Fragment(R.layout.repositories_fragment) {

    private lateinit var viewModel: RepositoryViewModel
    private lateinit var binding: RepositoriesFragmentBinding

    private val repositoryAdapter = RepositoriesAdapter()

    private val observerRepository =
        Observer<List<Repository>> {
            repositoryAdapter.update(it.toMutableList())
            binding.progressBar.visibility = View.GONE
            binding.repositoryRecyclerView.visibility = View.VISIBLE
        }

    private val observerPage = Observer<Int> {
        viewModel.getRepositoryList(page = it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = RepositoriesFragmentBinding.bind(view)

        viewModel = ViewModelProvider(this)[RepositoryViewModel::class.java]
        viewModel.repositories.observe(viewLifecycleOwner, observerRepository)
        viewModel.page.observe(viewLifecycleOwner, observerPage)

        setupRecyclerView()
        viewModel.getRepositoryList(1)
        //searchRepository()
    }

    private fun setupRecyclerView() = with(binding.repositoryRecyclerView) {
        adapter = repositoryAdapter
        layoutManager = LinearLayoutManager(requireContext())
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.nextPage()
                }
                hideSoftInput()
            }
        })
    }

    fun View.hideSoftInput() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}