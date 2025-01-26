package com.example.m7_geeco_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;  // Use this import
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;  // Make sure to use androidx.appcompat.widget.Toolbar here as well

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pruba);
        drawerLayout = findViewById(R.id.main2);
        navigationView = findViewById(R.id.nv_ejemplo);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  // Use setSupportActionBar to integrate the toolbar as the app bar
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.action_settings).setVisible(false);
        menu.findItem(R.id.ingresos).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nd_open, R.string.nd_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
        Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
