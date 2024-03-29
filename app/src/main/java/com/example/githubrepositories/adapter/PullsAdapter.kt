package com.example.githubrepositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.ItemPullBinding
import com.example.githubrepositories.model.Pull

class PullsAdapter() :
    ListAdapter<Pull, PullsViewHolder>(PullsDiffCallBack()) {

    private val pulls = mutableListOf<Pull>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_pull, parent, false).apply {
            return PullsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PullsViewHolder, position: Int) {
        getItem(position).let { pull ->
            holder.bind(pull)
        }
    }

    fun update(newList: MutableList<Pull>) {
        pulls.addAll(newList)
        submitList(newList)
        notifyDataSetChanged()
    }
}

class PullsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemPullBinding = ItemPullBinding.bind(itemView)

    fun bind(pull: Pull) {
        binding.tvPullTitle.text = pull.title
        binding.tvPullDescription.text = pull.body
        binding.tvOwner.text = pull.user.username
        binding.tvCreatedAt.text = pull.formattedDate()

        pull.user.let {
            Glide.with(itemView.context).load(it.avatarURL)
                .into(binding.ivUser)
        }
    }
}