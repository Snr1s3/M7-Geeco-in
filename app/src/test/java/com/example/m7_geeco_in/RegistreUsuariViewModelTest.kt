package com.example.m7_geeco_in
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.m7_geeco_in.viewModel.RegistreUsuariViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class RegistreUsuariViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `registre falla quan el nom es buit`() {
        val viewModel = RegistreUsuariViewModel()
        viewModel.registrarUsuari("")

        assertEquals("El nom d'usuari és obligatori", viewModel.errorNomUsuari.value)
        assertFalse(viewModel.registreComplet.value ?: true)
    }

    @Test
    fun `registre falla quan el nom conte espais`() {
        val viewModel = RegistreUsuariViewModel()
        viewModel.registrarUsuari("nom amb espais")

        assertEquals("No es permeten espais en el nom", viewModel.errorNomUsuari.value)
        assertFalse(viewModel.registreComplet.value ?: true)
    }

    @Test
    fun `registre falla quan el nom es massa curt`() {
        val viewModel = RegistreUsuariViewModel()
        viewModel.registrarUsuari("ab")

        assertEquals("Mínim 3 caràcters", viewModel.errorNomUsuari.value)
        assertFalse(viewModel.registreComplet.value ?: true)
    }

    @Test
    fun `registre falla quan el nom es massa llarg`() {
        val viewModel = RegistreUsuariViewModel()
        viewModel.registrarUsuari("a".repeat(21))

        assertEquals("Màxim 20 caràcters", viewModel.errorNomUsuari.value)
        assertFalse(viewModel.registreComplet.value ?: true)
    }

    @Test
    fun `registre falla quan el nom conte caracters invalids`() {
        val viewModel = RegistreUsuariViewModel()
        viewModel.registrarUsuari("user@name")

        assertEquals("Només lletres, números i _", viewModel.errorNomUsuari.value)
        assertFalse(viewModel.registreComplet.value ?: true)
    }

    @Test
    fun `registre exitos quan el nom es valid`() {
        val viewModel = RegistreUsuariViewModel()
        viewModel.registrarUsuari("Usuari_Valid123")

        assertNull(viewModel.errorNomUsuari.value)
        assertTrue(viewModel.registreComplet.value ?: false)
    }
}