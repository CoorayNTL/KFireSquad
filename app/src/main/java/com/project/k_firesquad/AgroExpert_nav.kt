package com.project.k_firesquad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.project.k_firesquad.activites.ExpertActionsActivity

import com.project.k_firesquad.databinding.FragmentAgroExpertNavBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AgroExpert_nav : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var btnExpertsActions :ImageButton


    private lateinit var binding: FragmentAgroExpertNavBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgroExpertNavBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnExpertsActions = binding.btnExpertsActions


        Log.d("hello", "intent.toString()")
        btnExpertsActions.setOnClickListener {
            val intent = Intent(activity, ExpertActionsActivity::class.java)
            Log.d("hello", "intent.toString()")
            startActivity(intent)
        }

    }


}