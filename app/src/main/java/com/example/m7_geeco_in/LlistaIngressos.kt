package com.example.m7_geeco_in

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LlistaIngressos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_ingressos)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ItemsView>()
        for (i in 1..10) {
            data.add(ItemsView(R.drawable.money, "Item $i"))
        }
        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter
    }
}