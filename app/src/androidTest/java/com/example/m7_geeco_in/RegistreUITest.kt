package com.example.m7_geeco_in

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.m7_geeco_in.inicisessio.activity_registre
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistreUITest {

    @Test
    fun registreAmbContrasenyesDiferents_mostraError() {
        ActivityScenario.launch(activity_registre::class.java)

        onView(withId(R.id.nom)).perform(typeText("Joan"))
        closeSoftKeyboard()
        onView(withId(R.id.email)).perform(typeText("joan@example.com"))
        closeSoftKeyboard()
        onView(withId(R.id.password1)).perform(typeText("Password1"))
        closeSoftKeyboard()
        onView(withId(R.id.password2)).perform(typeText("Password2"))
        closeSoftKeyboard()
        onView(withId(R.id.loginButton)).perform(click())

        Thread.sleep(1000)
        onView(withId(R.id.password2))
            .check(matches(hasErrorText("Les contrasenyes no coincideixen")))
    }
}