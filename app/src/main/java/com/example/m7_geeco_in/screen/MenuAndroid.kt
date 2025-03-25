package com.example.m7_geeco_in.screen

import android.os.Build
import android.view.Window
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MenuAndroid (private val window: Window) {

    fun hideSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars()) // Hides both status and navigation bars
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

    }

    /*   fun showSystemNavigationBar() {
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
               val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
               windowInsetsController.show(WindowInsetsCompat.Type.navigationBars())
           }
       }

     */
}