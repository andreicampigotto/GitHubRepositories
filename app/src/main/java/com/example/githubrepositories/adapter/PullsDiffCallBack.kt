package com.example.githubrepositories.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.githubrepositories.model.Pull

class PullsDiffCallBack: DiffUtil.ItemCallback<Pull>() {
    override fun areItemsTheSame(oldItem: Pull, newItem: Pull): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pull, newItem: Pull): Boolean {
        return oldItem.title == newItem.title
    }
}