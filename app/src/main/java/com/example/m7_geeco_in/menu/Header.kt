package com.example.m7_geeco_in.menu

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.m7_geeco_in.inicisessio.Login
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.inicisessio.Ajuda
import com.example.m7_geeco_in.inicisessio.IniciSessio
import com.example.m7_geeco_in.despesa.LlistaDespeses
import com.example.m7_geeco_in.informes.InforBoto
import com.example.m7_geeco_in.informes.StatsActivity
import com.example.m7_geeco_in.ingres.LlistaIngressos
import com.example.m7_geeco_in.preferencies.Preferencies
import com.example.m7_geeco_in.screen.MenuAndroid
import com.google.android.material.navigation.NavigationView

class Header : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MenuAndroid(window).hideSystemBar()
        setContentView(R.layout.activity_inicial)
        drawerLayout = findViewById(R.id.main2)
        navigationView = findViewById(R.id.nv_ejemplo)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.nd_open, R.string.nd_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.action_about)
        val button = findViewById<Button>(R.id.bingresos)
        button.setOnClickListener {
            InforBoto.estadistica.clicksIngresos++
            val intent = Intent(this@Header, LlistaIngressos::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.bdespese)
        button2.setOnClickListener {
            InforBoto.estadistica.clicksDespeses++
            val intent = Intent(this@Header, LlistaDespeses::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<Button>(R.id.banaisis)
        button3.setOnClickListener {
            val intent = Intent(this@Header, StatsActivity::class.java)
            startActivity(intent)
        }
        val button4 = findViewById<Button>(R.id.bobjectius)
        button4.setOnClickListener {
            InforBoto.estadistica.clicksObjectius++
            val intent = Intent(this@Header, StatsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        // Use if-else to check for the item ID and handle each case
        if (itemId == R.id.action_about) {
            val intent = Intent(this@Header, Ajuda::class.java)
            startActivity(intent)
        } else if (itemId == R.id.action_settings) {
            val intent = Intent(this@Header, Preferencies::class.java)
            startActivity(intent)
        } else if (itemId == R.id.despeses) {
            val intent = Intent(this@Header, LlistaDespeses::class.java)
            startActivity(intent)
        } else if (itemId == R.id.ingresos) {
            val intent = Intent(this@Header, LlistaIngressos::class.java)
            startActivity(intent)
        } else if (itemId == R.id.login) {
            val intent = Intent(this@Header, IniciSessio::class.java)
            startActivity(intent)
        } else if (itemId == R.id.logout) {
            val intent = Intent(this@Header, Login::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Invalid option", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

