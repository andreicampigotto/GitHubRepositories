package com.example.githubrepositories.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepositories.R
import com.example.githubrepositories.adapter.PullsAdapter
import com.example.githubrepositories.databinding.PullsFragmentBinding
import com.example.githubrepositories.model.Pull
import com.example.githubrepositories.viewModel.PullViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullFragment() : Fragment(R.layout.pulls_fragment) {

    private val args by navArgs<PullFragmentArgs>()
    private lateinit var viewModel: PullViewModel
    private lateinit var binding: PullsFragmentBinding
    private var adapter = PullsAdapter()
    private lateinit var recyclerView: RecyclerView

    private val observerPull = Observer<List<Pull>> {
        adapter.update(it.toMutableList())
        binding.progressBar.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PullsFragmentBinding.bind(view)

        viewModel = ViewModelProvider(this)[PullViewModel::class.java]

        recyclerView = binding.pullRecyclerView

        binding.pullRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.pullRecyclerView.adapter = adapter

        viewModel.pulls.observe(viewLifecycleOwner, observerPull)
        viewModel.getPullsList(args.repository!!.full_name)

        args.repository?.let {
            binding.repositoryName.text = it.full_name
            binding.repositoryDescriptionTextView.text = it.description
        }

        args.repository?.owner?.let {
            Glide.with(this).load(it.avatar_url)
                .into(binding.ivRepository)
        }
    }
}