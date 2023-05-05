package com.project.k_firesquad.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R
import com.project.k_firesquad.activites.BuyerDetailsActivity
import com.project.k_firesquad.adapter.BuyerAdapter
import com.project.k_firesquad.models.Buyer


//class SecondFragment : Fragment() {
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//
//
//
//    ): View? {
//        // Inflate the layout for this fragment
//
//        return inflater.inflate(R.layout.fragment_second, container, false)
//    }
//
//
//}

class SecondFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var buyerArrayList: ArrayList<Buyer>
    private lateinit var buyerAdapter: BuyerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_buyer)
        recyclerView.setHasFixedSize(true)
        buyerArrayList = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        buyerArrayList.add(Buyer(R.drawable.cotton, "cotton"))
        buyerArrayList.add(Buyer(R.drawable.cucumber, "cucumber"))
        buyerArrayList.add(Buyer(R.drawable.marigold, "marigold"))
        buyerArrayList.add(Buyer(R.drawable.non_gmo_soya_bean, "soya_bean"))
        buyerArrayList.add(Buyer(R.drawable.orange, "orange"))
        buyerArrayList.add(Buyer(R.drawable.ginga, "ginga"))
        buyerArrayList.add(Buyer(R.drawable.marigold, "marigold"))
        buyerArrayList.add(Buyer(R.drawable.cucumber, "cucumber"))
        buyerArrayList.add(Buyer(R.drawable.potato, "potato"))

        buyerAdapter = BuyerAdapter(buyerArrayList)
        recyclerView.adapter = buyerAdapter

        buyerAdapter.onItemClick = {
            val intent = Intent(requireContext(), BuyerDetailsActivity::class.java)
            intent.putExtra("name", it.name)
            startActivity(intent)
        }
    }
}
