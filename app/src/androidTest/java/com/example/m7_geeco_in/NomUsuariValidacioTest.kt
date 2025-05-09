package com.example.m7_geeco_in

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.m7_geeco_in.inicisessio.activity_registre
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NomUsuariValidacioTest {

    @Test
    fun mostraErrorPerNomCurt() {
        ActivityScenario.launch(activity_registre::class.java)

        val nomCurt = "ab"
        val email = "test@example.com"
        val pass = "Password1"

        onView(withId(R.id.nom)).perform(typeText(nomCurt))
        closeSoftKeyboard()
        onView(withId(R.id.email)).perform(typeText(email))
        closeSoftKeyboard()
        onView(withId(R.id.password1)).perform(typeText(pass))
        closeSoftKeyboard()
        onView(withId(R.id.password2)).perform(typeText(pass))
        closeSoftKeyboard()
        onView(withId(R.id.loginButton)).perform(click())

        Thread.sleep(1000)
        onView(withId(R.id.nom))
            .check(matches(hasErrorText("Mínim 3 caràcters")))
    }
}