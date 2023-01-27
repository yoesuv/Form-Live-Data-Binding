package com.yoesuv.formlivebinding

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiDevice

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
class ExampleInstrumentedTest {

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun register() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun startFlowPositive() {
        onView(withId(R.id.etEmail)).perform(typeText("apple@gmail.com"))
            .check(matches(withText("apple@gmail.com")))
        onView(withId(R.id.etEmail)).perform(clearText())
        onView(withId(R.id.textInputEmail)).check(matches(hasErrorText("Email is empty")))
        //onView(withId(R.id.etPassword)).perform(typeText("leMinerale"))

    }

}
