package com.yoesuv.formlivebinding

import android.content.Context
import android.os.SystemClock
import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class FormLoginTest {

    private val delay = 1000L
    private lateinit var context: Context

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun register() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun loginTest() {
        val btnLogin = onView(withId(R.id.btnLogin))
        val etEmail = onView(withId(R.id.etEmail))
        val etPassword = onView(withId(R.id.etPassword))

        btnLogin.check(matches(isNotEnabled()))
        etEmail.perform(typeText("apple@gmail.com"))
            .check(matches(withText("apple@gmail.com")))
        SystemClock.sleep(delay)
        etEmail.perform(clearText())
        onView(withText(context.getString(R.string.validation_email_empty))).check(matches(isDisplayed()))
        SystemClock.sleep(delay)
        etEmail.perform(typeText("apple@gmail.com."))
        SystemClock.sleep(delay)
        onView(withText(context.getString(R.string.validation_email_not_valid))).check(matches(isDisplayed()))
        etEmail.perform(pressKey(KeyEvent.KEYCODE_DEL))
        SystemClock.sleep(delay)
        etEmail.perform(closeSoftKeyboard())

        etPassword.perform(typeText("password"))
            .check(matches(withText("password")))
        SystemClock.sleep(delay)
        etPassword.perform(closeSoftKeyboard())
        SystemClock.sleep(delay)
        etPassword.perform(clearText())
        onView(withText(context.getString(R.string.validation_password_empty))).check(matches(isDisplayed()))
        btnLogin.check(matches(isNotEnabled()))
        etPassword.perform(typeText("pass"))
        onView(withText(context.getString(R.string.validation_password_min))).check(matches(isDisplayed()))
        etPassword.perform(closeSoftKeyboard())
        SystemClock.sleep(delay)
        etPassword.perform(typeText("word"))
        etPassword.perform(closeSoftKeyboard())
        SystemClock.sleep(delay)

        btnLogin.check(matches(isEnabled()))
        btnLogin.perform(click())
        SystemClock.sleep(delay)
        SystemClock.sleep(delay)
    }

}
