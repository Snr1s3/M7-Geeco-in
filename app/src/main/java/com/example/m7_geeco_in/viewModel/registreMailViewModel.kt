package com.example.m7_geeco_in.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class registreMailViewModel : ViewModel() {

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> = _isRegistered

    fun registre(email: String, password: String, password2: String) {
        if (isEmailValid(email) && password.isNotBlank() && password == password2) {
            _isRegistered.value = true
        } else {
            _isRegistered.value = false
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[^\\s]+@[^\\s]+\\.(com|es|net|org|edu|gov|info|cat)\$")
        return emailRegex.matches(email)
    }
}
