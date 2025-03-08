package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Filtres : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtres)
        val eT1 = findViewById<EditText>(R.id.editTextText)
        val eT2 = findViewById<EditText>(R.id.editTextText2)
        val eT3 = findViewById<EditText>(R.id.editTextText3)
        val b1 = findViewById<Button>(R.id.button1)
        b1.setOnClickListener{
            val intent = Intent(this@Filtres, LlistaIngressos::class.java)
            val bundle = Bundle()
            val text2 = eT2.text.toString()
            val text1 = eT1.text.toString()
            val text3 = eT3.text.toString()
            if (text1.isNotBlank()) {
                bundle.putString("fNom", text1)
                Toast.makeText(this, "Filtrant per: $text1", Toast.LENGTH_SHORT).show()
            }
            if (text2.isNotBlank()) {
                bundle.putString("fDiners", text2)
                Toast.makeText(this, "Filtrant per: $text2", Toast.LENGTH_SHORT).show()
            }
            if (text3.isNotBlank()) {
                bundle.putString("fData", text3)
                Toast.makeText(this, "Filtrant per: $text3", Toast.LENGTH_SHORT).show()
            }
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}