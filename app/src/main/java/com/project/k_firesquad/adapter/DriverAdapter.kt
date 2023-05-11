package com.project.k_firesquad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.databinding.ItemWithPosterBinding
import com.project.k_firesquad.models.VehicleData

const val ITEM_WITH_POSTER = 0

class DriverAdapter(private val mList: List<VehicleData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ItemWithPosterViewHolder(private val binding: ItemWithPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPosterView(vehicleData: VehicleData) {
            binding.vehicleType.text = vehicleData.vehicleType
            binding.vehiclePrice.text = vehicleData.price
            binding.vehicleOwner.text = vehicleData.owner
            binding.vehicleContact.text = vehicleData.contact
            binding.vehicleLocation.text = vehicleData.location
            binding.vehicleDescription.text = vehicleData.about
            binding.vehicleNo.text = vehicleData.vehicleNo
        }
    }

    override fun getItemViewType(position: Int): Int {
        return ITEM_WITH_POSTER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemWithPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemWithPosterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemWithPosterViewHolder).bindPosterView(mList[position])
    }
}