package com.example.m7_geeco_in

import android.os.Bundle
import android.view.View
import android.content.Intent;
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LlistaIngressos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_ingressos)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        val b1 = findViewById<Button>(R.id.button1)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ItemsView>()
        for (i in 1..20) {
            data.add(ItemsView(R.drawable.money, "Item $i"))
        }
        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter
        b1.setOnClickListener({
            val intent = Intent(this@LlistaIngressos, Filtres::class.java)
            startActivity(intent)
        })
    }
}