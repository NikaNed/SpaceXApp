package com.example.spacexapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.spacexapp.R
import com.example.spacexapp.domain.entities.LaunchesEntity

class LaunchesAdapter(private val listener: LaunchesListener)
    : PagingDataAdapter<LaunchesEntity, LaunchesViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_rv_launches,
            parent,
            false
        )
        return LaunchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }

    interface LaunchesListener{
        fun onClick(item: LaunchesEntity)
    }
}