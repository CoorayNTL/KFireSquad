package com.project.k_firesquad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multiplerecyclerviewmultiviewtype.RecyclerItem
import com.project.k_firesquad.databinding.BestSellerLayoutBinding
import com.project.k_firesquad.databinding.ProductLayoutBinding

class ChildAdapter(private val viewType: Int, private val recyclerItemList: List<RecyclerItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BestSellerViewHolder(private val binding: BestSellerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBestSellerView(recyclerItem: RecyclerItem) {
            binding.bestSellerImage.setImageResource(recyclerItem.image)
            binding.bestSellerText.text = recyclerItem.offer.
        }
    }

    inner class ProductViewHolder(private val binding: ProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindProductView(recyclerItem: RecyclerItem) {
            binding.productImage.setImageResource(recyclerItem.image)
          //  binding.productOffer.text = recyclerItem.offer
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataItemType.BEST_SELLER -> {
                val binding = BestSellerLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BestSellerViewHolder(binding)
            }
            else -> {
                val binding = ProductLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ProductViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is BestSellerViewHolder -> {
                holder.bindBestSellerView(recyclerItemList[position])
            }

            is ProductViewHolder -> {
                holder.bindProductView(recyclerItemList[position])
            }
        }
    }
}