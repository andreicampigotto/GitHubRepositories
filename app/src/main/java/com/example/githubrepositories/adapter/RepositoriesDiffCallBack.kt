package com.example.githubrepositories.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.githubrepositories.model.Repository

class RepositoriesDiffCallBack: DiffUtil.ItemCallback<Repository>()  {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }
}