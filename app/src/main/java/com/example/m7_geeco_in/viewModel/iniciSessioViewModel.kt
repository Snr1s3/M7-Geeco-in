package com.example.m7_geeco_in.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class iniciSessioViewModel : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun iniciarSessio(usuario: String, contrasenya: String) {
        _isLoggedIn.value = (usuario == "admin" && contrasenya == "1234")
    }

}