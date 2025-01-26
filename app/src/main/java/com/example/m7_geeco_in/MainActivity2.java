package com.example.m7_geeco_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pruba);

        // Initializing views
        drawerLayout = findViewById(R.id.main2);
        navigationView = findViewById(R.id.nv_ejemplo);
        toolbar = findViewById(R.id.toolbar);

        // Setting the toolbar as the app bar
        setSupportActionBar(toolbar);

        // Disable some menu items for now
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.action_settings).setVisible(false);
        menu.findItem(R.id.ingresos).setVisible(false);

        // Drawer toggle
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nd_open, R.string.nd_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set listener for navigation items
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.action_about);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        // Use if-else to check for the item ID and handle each case
        if (itemId == R.id.action_about) {
            // Open fragment or activity corresponding to "about"
            loadFragment("action_about");
        } else if (itemId == R.id.action_settings) {
            // Open fragment or activity corresponding to "settings"
            loadFragment("action_settings");
        } else if (itemId == R.id.despeses) {
            // Open fragment or activity corresponding to "despeses"
            loadFragment("despeses");
        } else if (itemId == R.id.ingresos) {
            // Open fragment or activity corresponding to "ingresos"
            loadFragment("ingresos");
        } else if (itemId == R.id.login) {
            // Open fragment or activity corresponding to "login"
            loadFragment("login");
        } else if (itemId == R.id.logout) {
            // Open fragment or activity corresponding to "logout"
            loadFragment("logout");
        } else {
            Toast.makeText(this, "Invalid option", Toast.LENGTH_SHORT).show();
        }

        // Close the drawer after selecting an item
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(String itemId) {
        // Example to load fragments dynamically based on the item id
        String fragmentName = itemId + ".xml";
        // Assuming you're using FragmentTransaction to dynamically load fragments
        // You can change this part to match your fragment setup.

        // Example of starting a new Activity for each item (if you're not using fragments)
        try {
            Class<?> fragmentClass = Class.forName("com.example.m7_geeco_in." + itemId);
            Intent intent = new Intent(MainActivity2.this, fragmentClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading fragment", Toast.LENGTH_SHORT).show();
        }
    }
}
