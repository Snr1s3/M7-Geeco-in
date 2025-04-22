package com.example.m7_geeco_in.viewModel



import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class registreViewModel : ViewModel() {

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> get() = _isRegistered

    fun registre(usuario: String, contrasenya: String, contrasenya2: String) {
       /* if(algo){
            _isRegistered.value = false
            return
        }*/
        _isRegistered.value = true
    }
}