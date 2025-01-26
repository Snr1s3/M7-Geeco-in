package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Header : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pruba)

        // Initializing views
        drawerLayout = findViewById(R.id.main2)
        navigationView = findViewById(R.id.nv_ejemplo)
        toolbar = findViewById(R.id.toolbar)

        // Setting the toolbar as the app bar
        setSupportActionBar(toolbar)

        // Disable some menu items for now
        val menu = navigationView.menu
        menu.findItem(R.id.action_settings)?.isVisible = false
        menu.findItem(R.id.ingresos)?.isVisible = false

        // Drawer toggle
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.nd_open, R.string.nd_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set listener for navigation items
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.action_about)
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
            val intent = Intent(this@Header, Despeses::class.java)
            startActivity(intent)
        } else if (itemId == R.id.ingresos) {
            val intent = Intent(this@Header, LlistaIngressos::class.java)
            startActivity(intent)
        } else if (itemId == R.id.login) {
            val intent = Intent(this@Header, IniciSessio::class.java)
            startActivity(intent)
        } else if (itemId == R.id.logout) {
            val intent = Intent(this@Header, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Invalid option", Toast.LENGTH_SHORT).show();
        }

        // Close the drawer after selecting an item
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(itemId: String) {
        // Example to load fragments dynamically based on the item id
        val fragmentName = "$itemId.xml"
        // Assuming you're using FragmentTransaction to dynamically load fragments
        // You can change this part to match your fragment setup.

        // Example of starting a new Activity for each item (if you're not using fragments)
        try {
            val fragmentClass = Class.forName("com.example.m7_geeco_in.$itemId")
            val intent = Intent(this@Header, fragmentClass)
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            Toast.makeText(this, "Error loading fragment", Toast.LENGTH_SHORT).show()
        }
    }
}

