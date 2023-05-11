package com.project.k_firesquad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.databinding.ItemWithPosterBinding
import com.project.k_firesquad.models.DataItem


const val ITEM_WITH_POSTER = 0
const val ITEM_WITHOUT_POSTER = 1


class DriverAdapter(private val mList: List<DataItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    inner class ItemWithPosterViewHolder(private val binding: ItemWithPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPosterView(dataItem: DataItem) {
            dataItem.image?.let { binding.itemImage.setImageResource(it) }
            binding.vehicleType.text = dataItem.vehicle_type
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (mList[position].image != null) {
            return ITEM_WITH_POSTER
        } else {
            return ITEM_WITHOUT_POSTER
        }
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
