package com.example.m7_geeco_in.informes

import android.app.Application
import android.provider.Settings
import android.util.Log


data class statistics(
    var clicksIngresos: Int = 0,
    var clicksDespeses: Int = 0,
    var clicksObjectius: Int = 0
)


class InforBoto : Application() {

    companion object {
        var idDispositiu = ""
        const val nomAplicacio = "Geeco-In"
        const val idAplicacio = "geecoin"
        var estadistica = statistics()
    }

    override fun onCreate() {
        super.onCreate()
        //https://developer.android.com/identity/user-data-ids
        idDispositiu = Settings.Secure.getString(
            getApplicationContext().contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }
    public fun saveStats() {
        Log.i("saveStats","Dades correctes")
    }

    public fun resetStats() {
        estadistica = statistics()
    }
}