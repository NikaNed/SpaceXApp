package com.example.spacexapp.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.spacexapp.R
import com.example.spacexapp.databinding.ItemRvLaunchesBinding
import com.example.spacexapp.domain.entities.LaunchesEntity


class LaunchesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRvLaunchesBinding.bind(view)

    fun bind(item: LaunchesEntity, listener: LaunchesAdapter.LaunchesListener) = with(binding) {

        with(item) {
            tvNameLaunch.text = name
            tvDate.text = itemView.context.getString(R.string.date_of_launch, date_utc)
            tvCountCores.text = itemView.context.getString(R.string.flights_repition, flight.toString())

            if (success == true) {
                tvStatus.text = itemView.context.getString(R.string.status, itemView.context.getString(R.string.success))
            } else {
                tvStatus.text = itemView.context.getString(R.string.status, itemView.context.getString(R.string.failure))
            }

            if (small != null) {
                ivLogoSmall.load(small)
            } else  ivLogoSmall.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_no_image))

            itemView.setOnClickListener {
                listener.onClick(item)
            }
        }
    }
}
