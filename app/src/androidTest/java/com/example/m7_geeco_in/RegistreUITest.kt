package com.example.m7_geeco_in

import android.view.WindowManager
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.m7_geeco_in.inicisessio.activity_registre
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistreUITest {

    @Test
    fun registreAmbContrasenyesDiferents_mostraToast() {
        ActivityScenario.launch(activity_registre::class.java)

        onView(withId(R.id.nom)).perform(typeText("Joan"))
        onView(withId(R.id.email)).perform(typeText("joan@example.com"))
        onView(withId(R.id.password1)).perform(typeText("Password1"))
        onView(withId(R.id.password2)).perform(typeText("Password2"))
        closeSoftKeyboard()
        onView(withId(R.id.loginButton)).perform(click())

        onView(withText("Les credencials son incorrectes"))
            .inRoot(withDecorView(not(`is`(getCurrentActivity()?.window?.decorView))))
            .check(matches(isDisplayed()))
    }

    private fun getCurrentActivity(): android.app.Activity? {
        var currentActivity: android.app.Activity? = null
        androidx.test.core.app.ActivityScenario.launch(activity_registre::class.java).onActivity {
            currentActivity = it
            it.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        return currentActivity
    }
}