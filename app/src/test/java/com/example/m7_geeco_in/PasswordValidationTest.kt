import com.example.m7_geeco_in.viewModel.registreViewModel
import org.junit.Assert.*
import org.junit.Before
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
    fun nombreUsuarioVacio() {
        viewModel.registre("", "user@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreUsuarioConEspacios() {
        viewModel.registre("mi usuario", "user@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreUsuarioDemasiadoCorto() {
        viewModel.registre("ab", "user@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreUsuarioDemasiadoLargo() {
        viewModel.registre("aaaaaaaaaaaaaaaaaaaaa", "user@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreUsuarioConCaracteresInvalidos() {
        viewModel.registre("usuario!@", "user@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreConMultiplesGuiones() {
        viewModel.registre("usuari_test_123", "user@example.com", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreUsuarioConMultiplesGuionesBajosSeguidos() {
        viewModel.registre("usuari____prova", "user@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreUsuarioValido() {
        viewModel.registre("usuario_123", "user@example.com", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreSoloMayusculas() {
        viewModel.registre("USERNAME", "user@example.com", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun nombreSoloMinusculas() {
        viewModel.registre("usuari", "user@example.com", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun passwordsDoNotMatch_setsIsRegisteredFalse() {
        viewModel.registre("user", "user@example.com","Password1", "Password2", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun passwordTooShort_setsIsRegisteredFalse() {
        viewModel.registre("user", "user@example.com","Pass1", "Pass1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun passwordMissingUppercase_setsIsRegisteredFalse() {
        viewModel.registre("user", "user@example.com","password1", "password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun passwordMissingNumber_setsIsRegisteredFalse() {
        viewModel.registre("user", "user@example.com","Password", "Password", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun validPassword_setsIsRegisteredTrue() {
        viewModel.registre("user", "user@example.com","Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }
    @Test
    fun emailCorrectoCom() {
        viewModel.registre("user","user@example.com", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun emailCorrectoEs() {
        viewModel.registre("user","usuari@domini.es", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun emailCorrectoCat() {
        viewModel.registre("user","user.name@domain.cat", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }

    @Test
    fun extensionInvalidaDev() {
        viewModel.registre("user","user@domain.dev", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun sinArroba() {
        viewModel.registre("user","userexample.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun sinPunto() {
        viewModel.registre("user","user@examplecom", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun espacioEnElNombre() {
        viewModel.registre("user","user name@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun espacioAntes() {
        viewModel.registre("user"," user@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun espacioDespues() {
        viewModel.registre("user","user@example.com ", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun sinDominioAntesDelPunto() {
        viewModel.registre("user","user@.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun sinNombreUsuario() {
        viewModel.registre("user","@example.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun espacioEnDominio() {
        viewModel.registre("user","user@exam ple.com", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test
    fun dominioMuyLargoInvalido() {
        viewModel.registre("user","user@example.toolongextension", "Password1", "Password1", true)
        assertFalse(viewModel.isRegistered.value!!)
    }

    @Test fun unicodeEnUsuario() {
        viewModel.registre("user","usér@example.com", "Password1", "Password1", true)
        assertTrue(viewModel.isRegistered.value!!)
    }
}