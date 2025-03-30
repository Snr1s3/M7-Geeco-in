package com.example.m7_geeco_in.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.m7_geeco_in.menu.Header
import com.example.m7_geeco_in.preferencies.Preferencies
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.despesa.LlistaDespeses
import com.example.m7_geeco_in.ingres.LlistaIngressos

class fragment_bottom : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_bottom, container, false)

        view.findViewById<ImageButton>(R.id.but1)
            .setOnClickListener {
                val intent = Intent(activity, LlistaDespeses::class.java)
                startActivity(intent)
            }
        view.findViewById<ImageButton>(R.id.but2)
            .setOnClickListener {
                val intent = Intent(activity, LlistaIngressos::class.java)
                startActivity(intent)
            }
        view.findViewById<ImageButton>(R.id.but3)
            .setOnClickListener {
                val intent = Intent(activity, Header::class.java)
                startActivity(intent)
            }
        view.findViewById<ImageButton>(R.id.but4)
            .setOnClickListener {
                val intent = Intent(activity, Preferencies::class.java)
                startActivity(intent)
            }
        return view
    }
}