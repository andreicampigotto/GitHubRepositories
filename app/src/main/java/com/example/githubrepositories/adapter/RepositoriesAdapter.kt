package com.example.githubrepositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.ItemRepositoryBinding
import com.example.githubrepositories.model.Repository

class RepositoriesAdapter(val onTap: (Repository) -> Unit) :
    ListAdapter<Repository, RepositoryViewHolder>(RepositoriesDiffCallBack()) {

    private var repositories = mutableListOf<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false).apply {
            return RepositoryViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        repositories[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener {
                onTap(
                    this
                )
            }
        }
    }
}

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemRepositoryBinding = ItemRepositoryBinding.bind(itemView)

    fun bind(repository: Repository) {
        binding.tvRepositoryTitle.text = repository.name
        binding.tvRepositoryDescription.text = repository.description
        binding.tvForkRepository.text = repository.forks_count.toString()
        binding.tvStarsRepository.text = repository.stargazers_count.toString()
        binding.tvViewsRepositoryWatchers.text = repository.watchers_count.toString()
        binding.tvUsernameRepository.text = repository.owner.login
        binding.tvNameLastnameRepository.text = repository.owner.html_url

        repository.owner.let {
            Glide.with(itemView.context).load(it.avatar_url)
                .into(binding.imageView)
        }
    }
}