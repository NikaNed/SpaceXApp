package com.example.spacexapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.spacexapp.domain.entities.LaunchesEntity

object DiffUtilCallback : DiffUtil.ItemCallback<LaunchesEntity>() {
    override fun areItemsTheSame(oldItem: LaunchesEntity, newItem: LaunchesEntity): Boolean {
            return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LaunchesEntity, newItem: LaunchesEntity): Boolean {
        return oldItem == newItem
    }
}
