package com.example.m7_geeco_in.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistreUsuariViewModel : ViewModel() {
    private val _registreComplet = MutableLiveData<Boolean>()
    val registreComplet: LiveData<Boolean> get() = _registreComplet

    private val _errorNomUsuari = MutableLiveData<String?>()
    val errorNomUsuari: LiveData<String?> get() = _errorNomUsuari

    fun registrarUsuari(nomUsuari: String) {
        _errorNomUsuari.value = null
        _registreComplet.value = false

        if (nomUsuari.trim().isEmpty()) {
            _errorNomUsuari.value = "El nom d'usuari és obligatori"
            _registreComplet.value = false
            return
        }

        if (nomUsuari.contains(" ")) {
            _errorNomUsuari.value = "No es permeten espais en el nom"
            return
        }

        if (nomUsuari.length < 3) {
            _errorNomUsuari.value = "Mínim 3 caràcters"
            _registreComplet.value = false
            return
        }

        if (nomUsuari.length >= 20) {
            _errorNomUsuari.value = "Màxim 20 caràcters"
            _registreComplet.value = false
            return
        }

        if (!nomUsuari.matches(Regex("^[a-zA-Z0-9_]+$"))) {
            _errorNomUsuari.value = "Només lletres, números i _"
            _registreComplet.value = false
            return
        }

        _errorNomUsuari.value = null
        _registreComplet.value = true
    }
}