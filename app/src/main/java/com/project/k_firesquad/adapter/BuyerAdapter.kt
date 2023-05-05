package com.project.k_firesquad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R
import com.project.k_firesquad.models.Buyer

class BuyerAdapter (private val BuyerList: ArrayList<Buyer>): RecyclerView.Adapter<BuyerAdapter.BuyerViewHolder>(){


    var onItemClick : ((Buyer) -> Unit)? = null

    class BuyerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.iv_buyer_image)
        val name: TextView = itemView.findViewById(R.id.tv_buyer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_buyer,parent,false)
        return BuyerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BuyerViewHolder, position: Int) {
        val currentItem = BuyerList[position]
        holder.image.setImageResource(currentItem.image)
        holder.name.text = currentItem.name

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(BuyerList[position])
            //Toast.makeText(holder.itemView.context, "You clicked on item #${position + 1}", Toast.LENGTH_SHORT).show()


        }
    }

    override fun getItemCount() = BuyerList.size
}