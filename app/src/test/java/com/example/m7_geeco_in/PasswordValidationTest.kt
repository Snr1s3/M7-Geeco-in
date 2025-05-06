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
        assertTrue(viewModel.isRegistered.value!!)
    }
}