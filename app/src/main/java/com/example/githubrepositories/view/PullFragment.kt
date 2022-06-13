package com.example.githubrepositories.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepositories.R
import com.example.githubrepositories.adapter.PullsAdapter
import com.example.githubrepositories.databinding.PullsFragmentBinding
import com.example.githubrepositories.model.Pull
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.viewModel.PullViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullFragment(val repository: Repository) : Fragment(R.layout.pulls_fragment) {

    private lateinit var viewModel: PullViewModel
    private lateinit var binding: PullsFragmentBinding
    private var adapter = PullsAdapter()

    private val observerPull = Observer<List<Pull>> {
        adapter.update(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PullsFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this)[PullViewModel::class.java]

        binding.pullRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.pullRecyclerView.adapter = adapter

        viewModel.pulls.observe(viewLifecycleOwner, observerPull)
        viewModel.getPullsList("repository_name")
    }
}