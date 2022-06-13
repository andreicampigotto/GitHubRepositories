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

class PullsAdapter:
    ListAdapter<Pull, PullsViewHolder>(PullsDiffCallBack()) {

    private val pulls = mutableListOf<Pull>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_pull, parent, false).apply {
            return PullsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PullsViewHolder, position: Int) {
        getItem(position).let {
            holder.binding(it)
        }
    }

    fun update(newList: List<Pull>) {
        pulls.clear()
        pulls.addAll(newList)
        notifyDataSetChanged()
    }
}

class PullsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemPullBinding = ItemPullBinding.bind(itemView)

    fun binding(pull: Pull) {
        binding.tvPullTitle.text = pull.title
        binding.tvPullDescription.text = pull.body
        binding.tvUsernamePull.text = pull.owner.login

        pull.owner.let {
            Glide.with(itemView.context).load(it.avatar_url)
                .into(binding.imageView)
        }
    }
}