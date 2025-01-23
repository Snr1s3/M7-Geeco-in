package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FiltresDespeses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtres_despeses)
        val eT1 = findViewById<EditText>(R.id.editTextText)
        val eT2 = findViewById<EditText>(R.id.editTextText2)
        val b1 = findViewById<Button>(R.id.button1)
        b1.setOnClickListener{
            val intent = Intent(this@FiltresDespeses, LlistaDespeses::class.java)
            val bundle = Bundle()
            val text2 = eT2.text.toString()
            val text1 = eT1.text.toString()
            if(!text2.isBlank() && text1.isBlank()){
                bundle.putString("fNom", text2)
                intent.putExtras(bundle)
                Toast.makeText(this, "Filtrant per:" +text2, Toast.LENGTH_SHORT).show()
            }
            else if(!text1.isBlank() && text2.isBlank()){
                bundle.putString("fDiners", text1)
                intent.putExtras(bundle)
                Toast.makeText(this, "Filtrant per:" +text1, Toast.LENGTH_SHORT).show()
            }
            else if(!text1.isBlank() && !text2.isBlank()){
                bundle.putString("fNom", text2)
                bundle.putString("fDiners", text1)
                intent.putExtras(bundle)
                Toast.makeText(this, "Filtrant per:" +text1 + " i per: "+text2, Toast.LENGTH_SHORT).show()
            }
            startActivity(intent)
        }
    }
}