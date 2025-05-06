package com.example.m7_geeco_in.viewModel



import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class registreViewModel : ViewModel() {

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> get() = _isRegistered
    val upperCase = Regex("\\p{Lu}")
    val nums = Regex("[0-9]")

    fun registre(nom: String, mail:String, contrasenya: String, contrasenya2: String,  isTest: Boolean = false) {
        /* if(algo){
            _isRegistered.value = false
            return
        }*/
        if (contrasenya != contrasenya2) {
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