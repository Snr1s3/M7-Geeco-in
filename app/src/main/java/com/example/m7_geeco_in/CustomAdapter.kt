package com.example.m7_geeco_in

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val context: Context, private val mList: List<ItemsView>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.imageView.setImageResource(ItemsViewModel.image)

        holder.textView.text = ItemsViewModel.text
        holder.textView2.text = ItemsViewModel.euro
        holder.itemView.setOnClickListener {
            val intent = when (context) {
                is LlistaIngressos -> Intent(context, ModificarIngres::class.java)
                is LlistaDespeses -> Intent(context, modificar_despesa::class.java)
                else -> null
            }
            intent?.let {
                it.putExtra("ITEM_ID", ItemsViewModel.id) // Pass the ID
                context.startActivity(it)
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textView2: TextView = itemView.findViewById(R.id.textView3)
    }
}

