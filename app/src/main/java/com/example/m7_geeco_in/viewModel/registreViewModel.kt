package com.example.m7_geeco_in.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class registreViewModel : ViewModel() {
    private val _errorNomUsuari = MutableLiveData<String?>()
    val errorNomUsuari: LiveData<String?> get() = _errorNomUsuari

    private val _errorContrasenya = MutableLiveData<String?>()
    val errorContrasenya: LiveData<String?> get() = _errorContrasenya

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> get() = _isRegistered

    val upperCase = Regex("\\p{Lu}")
    val nums = Regex("[0-9]")

    fun registre(nom: String, mail:String, contrasenya: String, contrasenya2: String,  isTest: Boolean = false) {
        _errorNomUsuari.value = null
        _errorContrasenya.value = null
        _isRegistered.value = false

        if (nom.trim().isEmpty()) {
            _errorNomUsuari.value = "El nom d'usuari és obligatori"
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: Nom d'usuari buit")
            return
        }

        if (nom.contains(" ")) {
            _errorNomUsuari.value = "No es permeten espais en el nom"
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: Nom conté espais")
            return
        }

        if (nom.length < 3) {
            _errorNomUsuari.value = "Mínim 3 caràcters"
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: Nom massa curt")
            return
        }

        if (nom.length >= 20) {
            _errorNomUsuari.value = "Màxim 20 caràcters"
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: Nom massa llarg")
            return
        }

        if (!nom.matches(Regex("^[a-zA-Z0-9_]+$"))) {
            _errorNomUsuari.value = "Només lletres, números i _"
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: Caràcters invàlids")
            return
        }

        if (nom.contains(Regex("___+"))) {
            _errorNomUsuari.value = "No es permet _ dues vegades"
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: No es permeten més de dos guions baixos seguits")
            return
        }

        if (contrasenya != contrasenya2) {
            _errorContrasenya.value = "Les contrasenyes no coincideixen"
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: Les contrasenyes no coincideixen")
            return
        }
        if (contrasenya.length < 8) {
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: La contrasenya ha de tenir més de 8 caràcters")
            return
        }
        if (!upperCase.containsMatchIn(contrasenya)) {
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: La contrasenya ha de tenir caracters en majuscules")
            return
        }
        if (!nums.containsMatchIn(contrasenya)) {
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: La contrasenya ha de tenir numeros")
            return
        }
        if (!isEmailValid(mail)) {
            _isRegistered.value = false
            if (!isTest) Log.d("registreViewModel", "Testing: Mail incorrecte")
            return
        }

        _isRegistered.value = true
        if (!isTest) Log.d("registreViewModel", "User registered successfully")
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[^\\s]+@[^\\s]+\\.(com|es|net|org|edu|gov|info|cat)\$")
        return emailRegex.matches(email)
    }
}