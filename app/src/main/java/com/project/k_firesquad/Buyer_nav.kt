package com.project.k_firesquad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.project.k_firesquad.activites.BuyerAddProductActivity
import com.project.k_firesquad.activites.BuyerMarketPlaceActivity
import com.project.k_firesquad.activites.BuyerProductsListActivity
import com.project.k_firesquad.databinding.FragmentBuyerNavBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Buyer_nav : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var addProductBtn: ImageButton
    private lateinit var viewMyProducts: ImageButton
    private lateinit var marketPlace: ImageButton
    private lateinit var binding: FragmentBuyerNavBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuyerNavBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addProductBtn = binding.addBuyerProduct
        viewMyProducts = binding.viewBuyerProducts
        marketPlace = binding.marketPlace

        Log.d("hello", "intent.toString()")
        addProductBtn.setOnClickListener {
            val intent = Intent(activity, BuyerAddProductActivity::class.java)
            Log.d("hello", "intent.toString()")
            startActivity(intent)
        }
        viewMyProducts.setOnClickListener {
            val intent = Intent(activity, BuyerProductsListActivity::class.java)
            startActivity(intent)
        }
        marketPlace.setOnClickListener {
            val intent = Intent(activity, BuyerMarketPlaceActivity::class.java)
            startActivity(intent)
        }
    }


}