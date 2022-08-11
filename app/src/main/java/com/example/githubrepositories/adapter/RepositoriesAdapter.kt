package com.example.githubrepositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.ItemRepositoryBinding
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.view.RepositoryFragmentDirections

class RepositoriesAdapter(val onTap: (Repository) -> Unit) :
    RecyclerView.Adapter<RepositoryViewHolder>() {

    private var repositories = mutableListOf<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false).apply {
            return RepositoryViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        repositories[position].let { repository ->
            holder.bind(repository)
            holder.itemView.setOnClickListener {
                it.findNavController().navigate(RepositoryFragmentDirections.actionRepositoryFragmentToPullFragment(repository))
            }
        }
    }

    override fun getItemCount(): Int = repositories.size

    fun update(newList: MutableList<Repository>) {
        repositories.addAll(newList)
        notifyDataSetChanged()
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

        repository.owner.let {
            Glide.with(itemView.context).load(it.avatar_url)
                .into(binding.ivRepository)
        }
    }
}