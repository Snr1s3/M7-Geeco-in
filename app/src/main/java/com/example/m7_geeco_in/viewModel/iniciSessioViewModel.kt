package com.example.m7_geeco_in.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.util.Assert
import org.junit.jupiter.api.Test
import org.junit.Assert.*

class iniciSessioViewModel : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun iniciarSessio(usuario: String, contrasenya: String) {
        _isLoggedIn.value = (usuario == "admin" && contrasenya == "1234")
    }
    @Test
    fun emailCorrectoCom() {
        assertTrue(isEmailValid("user@example.com"))
    }

    @Test
    fun emailCorrectoEs() {
        assertTrue(isEmailValid("usuari@domini.es"))
    }

    @Test
    fun emailCorrectoCat() {
        assertTrue(isEmailValid("user.name@domain.cat"))
    }

    @Test
    fun extensionInvalidaDev() {
        assertFalse(isEmailValid("user@domain.dev"))
    }

    @Test
    fun sinArroba() {
        assertFalse(isEmailValid("userexample.com"))
    }

    @Test
    fun sinPunto() {
        assertFalse(isEmailValid("user@examplecom"))
    }

    @Test
    fun espacioEnElNombre() {
        assertFalse(isEmailValid("user name@example.com"))
    }

    @Test
    fun espacioAntes() {
        assertFalse(isEmailValid(" user@example.com"))
    }

    @Test
    fun espacioDespues() {
        assertFalse(isEmailValid("user@example.com "))
    }

    @Test
    fun sinDominioAntesDelPunto() {
        assertFalse(isEmailValid("user@.com"))
    }

    @Test
    fun sinNombreUsuario() {
        assertFalse(isEmailValid("@example.com"))
    }

    @Test
    fun espacioEnDominio() {
        assertFalse(isEmailValid("user@exam ple.com"))
    }

    @Test
    fun dominioMuyLargoInvalido() {
        assertFalse(isEmailValid("user@example.toolongextension"))
    }

    @Test fun unicodeEnUsuario() {
        assertTrue(isEmailValid("usér@example.com"))
    }
}