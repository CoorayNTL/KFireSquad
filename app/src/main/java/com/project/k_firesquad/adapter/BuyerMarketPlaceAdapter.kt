package com.project.k_firesquad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R
import com.project.k_firesquad.models.BuyerProduct

class BuyerMarketPlaceAdapter (private var buyerMarketPlaceList: ArrayList<BuyerProduct>) : RecyclerView.Adapter<BuyerMarketPlaceAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setFilteredList(mList: ArrayList<BuyerProduct>){
        this.buyerMarketPlaceList = mList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyerMarketPlaceAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.buyer_product, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: BuyerMarketPlaceAdapter.ViewHolder, position: Int) {
        val currentProduct = buyerMarketPlaceList[position]
        holder.tvBuyerProductName.text = currentProduct.productName
        holder.tvBuyerQtyValue.text = currentProduct.productQty
        holder.tvBuyerRateValue.text = "Rs.${currentProduct.productRate}/1Kg"
        holder.tvBuyerContactValue.text = currentProduct.sellerLocation
    }


    override fun getItemCount(): Int {
        return buyerMarketPlaceList.size
    }


    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {

        val tvBuyerProductName: TextView = itemView.findViewById(R.id.tvBuyerProductName)
        val tvBuyerQtyValue: TextView = itemView.findViewById(R.id.tvBuyerQtyValue)
        val tvBuyerRateValue: TextView = itemView.findViewById(R.id.tvBuyerRateValue)
        val tvBuyerContactValue: TextView = itemView.findViewById(R.id.tvBuyerContactValue)
        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }
}