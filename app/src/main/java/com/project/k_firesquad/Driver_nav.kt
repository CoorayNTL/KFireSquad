package com.project.k_firesquad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.project.k_firesquad.activites.DriverActivityProfile
import com.project.k_firesquad.activites.RegisterActivity
import com.project.k_firesquad.databinding.FragmentDriverNavBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Driver_nav : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var regButton:Button
    private lateinit var loginButton:Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var binding: FragmentDriverNavBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDriverNavBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = binding.email
        password = binding.password
        regButton = binding.regButton
        loginButton = binding.btnlogin



        Log.d("hello", "intent.toString()")
        loginButton.setOnClickListener {
            val intent = Intent(activity, DriverActivityProfile::class.java)
            Log.d("hello", "intent.toString()")
            startActivity(intent)
        }
        regButton.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


}