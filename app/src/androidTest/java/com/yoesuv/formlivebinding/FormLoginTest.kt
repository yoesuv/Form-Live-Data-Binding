package com.yoesuv.formlivebinding

import android.content.Context
import android.os.SystemClock
import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiDevice

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FormLoginTest {

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val delay = 1000L
    private lateinit var context: Context

    @Before
    fun register() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun startFlowPositive() {
        val btnLogin = onView(withId(R.id.btnLogin))
        val etPassword = onView(withId(R.id.etPassword))

        btnLogin.check(matches(isNotEnabled()))
        onView(withId(R.id.etEmail)).perform(typeText("apple@gmail.com"))
            .check(matches(withText("apple@gmail.com")))
        SystemClock.sleep(delay)
        onView(withId(R.id.etEmail)).perform(clearText())
        onView(withText(context.getString(R.string.validation_email_empty))).check(matches(isDisplayed()))
        SystemClock.sleep(delay)
        onView(withId(R.id.etEmail)).perform(typeText("apple@gmail.com."))
        SystemClock.sleep(delay)
        onView(withText(context.getString(R.string.validation_email_not_valid))).check(matches(isDisplayed()))
        onView(withId(R.id.etEmail)).perform(pressKey(KeyEvent.KEYCODE_DEL))
        SystemClock.sleep(delay)
        onView(withId(R.id.etEmail)).perform(closeSoftKeyboard())

        etPassword.perform(typeText("password"))
            .check(matches(withText("password")))
        SystemClock.sleep(delay)
        etPassword.perform(closeSoftKeyboard())
        SystemClock.sleep(delay)

        btnLogin.check(matches(isEnabled()))
        btnLogin.perform(click())
        SystemClock.sleep(delay)
        SystemClock.sleep(delay)
    }

}
