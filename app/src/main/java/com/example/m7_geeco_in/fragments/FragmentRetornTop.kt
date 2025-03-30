package com.example.m7_geeco_in.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.m7_geeco_in.R

class RetornTop : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_retorn_top, container, false)

        // Configurar el botó returnBoto
        val returnBoto = view.findViewById<ImageButton>(R.id.returnBoto) // Assegura't que l'ID coincideixi amb el del teu layout
        returnBoto.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return view
    }
}