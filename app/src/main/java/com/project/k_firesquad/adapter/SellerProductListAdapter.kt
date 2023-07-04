package com.project.k_firesquad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R
import com.project.k_firesquad.models.SellerProduct

class SellerProductListAdapter (private var sellerProductsList: ArrayList<SellerProduct>) : RecyclerView.Adapter<SellerProductListAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setFilteredList(mList: ArrayList<SellerProduct>){
        this.sellerProductsList = mList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerProductListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.seller_product, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: SellerProductListAdapter.ViewHolder, position: Int) {
        val currentProduct = sellerProductsList[position]
        holder.tvSellerProductName.text = currentProduct.productName
        holder.tvSellerQtyValue.text = currentProduct.productQty
        holder.tvSellerRateValue.text = "Rs.${currentProduct.productRate}/1Kg"
        holder.tvSellerContactValue.text = currentProduct.sellerLocation
    }


    override fun getItemCount(): Int {
        return sellerProductsList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {

        val tvSellerProductName: TextView = itemView.findViewById(R.id.tvSellerProductName)
        val tvSellerQtyValue: TextView = itemView.findViewById(R.id.tvSellerQtyValue)
        val tvSellerRateValue: TextView = itemView.findViewById(R.id.tvSellerRateValue)
        val tvSellerContactValue: TextView = itemView.findViewById(R.id.tvSellerContactValue)
        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }
}