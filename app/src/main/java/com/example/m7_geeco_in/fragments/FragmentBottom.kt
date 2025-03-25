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
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [fragment_bottom.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_bottom : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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