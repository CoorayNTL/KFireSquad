package com.project.k_firesquad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.project.k_firesquad.activites.SellerAddProductActivity
import com.project.k_firesquad.activites.SellerMarketPlaceActivity
import com.project.k_firesquad.activites.SellerProductsListActivity
import com.project.k_firesquad.databinding.FragmentFarmerNavBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Farmer_nav : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var addProductBtn: ImageButton
    private lateinit var viewMyProducts: ImageButton
    private lateinit var marketPlace: ImageButton
    private lateinit var binding: FragmentFarmerNavBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFarmerNavBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addProductBtn = binding.addSellerProduct
        viewMyProducts = binding.viewSellerProducts
        marketPlace = binding.marketPlace

        Log.d("hello", "intent.toString()")
        addProductBtn.setOnClickListener {
            val intent = Intent(activity, SellerAddProductActivity::class.java)
            Log.d("hello", "intent.toString()")
            startActivity(intent)
        }
        viewMyProducts.setOnClickListener {
            val intent = Intent(activity, SellerProductsListActivity::class.java)
            startActivity(intent)
        }
        marketPlace.setOnClickListener {
            val intent = Intent(activity, SellerMarketPlaceActivity::class.java)
            startActivity(intent)
        }
    }


}