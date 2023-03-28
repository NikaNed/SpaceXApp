package com.example.spacexapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexapp.R
import com.example.spacexapp.databinding.ItemRvCrewBinding
import com.example.spacexapp.domain.entities.CrewEntity

class CrewAdapter: ListAdapter<CrewEntity, CrewViewHolder>(DiffUtilCallbackCrew) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_rv_crew,
            parent,
            false
        )
        return CrewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CrewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRvCrewBinding.bind(view)

    fun bind(item: CrewEntity) = with(binding) {
        with(item) {
            tvCrewName.text = name
            tvCrewAgency.text = itemView.context.getString(R.string.crew_agency, agency)
            tvCrewStatus.text = itemView.context.getString(R.string.crew_status, status)
            if (status == "active") {
                tvCrewStatus.text = itemView.context.getString(R.string.active)
            } else tvCrewStatus.text = itemView.context.getString(R.string.inactive)

        }
    }
}

object DiffUtilCallbackCrew : DiffUtil.ItemCallback<CrewEntity>() {
    override fun areItemsTheSame(oldItem: CrewEntity, newItem: CrewEntity): Boolean {
            return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CrewEntity, newItem: CrewEntity): Boolean {
        return oldItem == newItem
    }
}