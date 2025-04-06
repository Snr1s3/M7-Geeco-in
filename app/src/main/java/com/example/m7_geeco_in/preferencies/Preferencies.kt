package com.example.m7_geeco_in.preferencies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.SplashActivity
import com.example.m7_geeco_in.menu.Header
import com.example.m7_geeco_in.screen.MenuAndroid

class Preferencies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MenuAndroid(window).hideSystemBar()
        setContentView(R.layout.activity_preferencies)
        val b1 = findViewById<Button>(R.id.button)
        val switchModeFosc = findViewById<Switch>(R.id.switch2)
        val switchAmagats = findViewById<Switch>(R.id.switch3)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val sharedPref = getSharedPreferences("PreferenciesUsuari", Context.MODE_PRIVATE)
        switchModeFosc.isChecked = sharedPref.getBoolean("mode_fosc", false)
        switchAmagats.isChecked = sharedPref.getBoolean("amagats", false)
        seekBar.progress = sharedPref.getInt("nivell_seekbar", 0)
        b1.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("mode_fosc", switchModeFosc.isChecked)
            editor.putBoolean("amagats", switchAmagats.isChecked)
            editor.putInt("nivell_seekbar", seekBar.progress)
            editor.apply()
            val intent = Intent(this@Preferencies, Header::class.java)
            startActivity(intent)
        }
    }
}
