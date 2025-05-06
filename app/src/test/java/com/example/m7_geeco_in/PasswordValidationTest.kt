import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.m7_geeco_in.viewModel.registreViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class registreViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: registreViewModel

    @Before
    fun setup() {
        viewModel = registreViewModel()
    }

    @Test
    fun passwordsDoNotMatch_setsIsRegisteredFalse() {
        viewModel.registre("user", "Password1", "Password2", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun passwordTooShort_setsIsRegisteredFalse() {
        viewModel.registre("user", "Pass1", "Pass1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun passwordMissingUppercase_setsIsRegisteredFalse() {
        viewModel.registre("user", "password1", "password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun passwordMissingNumber_setsIsRegisteredFalse() {
        viewModel.registre("user", "Password", "Password", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun validPassword_setsIsRegisteredTrue() {
        viewModel.registre("user", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }
    @Test
    fun emailCorrectoCom() {
        viewModel.registre("user@example.com", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun emailCorrectoEs() {
        viewModel.registre("usuari@domini.es", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun emailCorrectoCat() {
        viewModel.registre("user.name@domain.cat", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun extensionInvalidaDev() {
        viewModel.registre("user@domain.dev", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun sinArroba() {
        viewModel.registre("userexample.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun sinPunto() {
        viewModel.registre("user@examplecom", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun espacioEnElNombre() {
        viewModel.registre("user name@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun espacioAntes() {
        viewModel.registre(" user@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun espacioDespues() {
        viewModel.registre("user@example.com ", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun sinDominioAntesDelPunto() {
        viewModel.registre("user@.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun sinNombreUsuario() {
        viewModel.registre("@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun espacioEnDominio() {
        viewModel.registre("user@exam ple.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun dominioMuyLargoInvalido() {
        viewModel.registre("user@example.toolongextension", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test fun unicodeEnUsuario() {
        viewModel.registre("usér@example.com", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }
}