package com.project.k_firesquad

import android.content.Intent
import android.widget.TextView

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.project.k_firesquad.activities.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class MyPostsInstrumentedTest {

    @Test
    fun testTvHeaderExpertActions() {

        // Start the activity
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val activityScenario = ActivityScenario.launch<MainActivity>(intent)

        // Find and click the view posts button
        onView(withId(R.id.btnExpertsActions)).perform(click())

        // Check that the text of tvHeader in the next screen is "Agri Exprts' Dashboard"
        onView(withId(R.id.textView)).check { view, _ ->
            assertEquals(
                "Agri Experts' Dashboard",
                view?.findViewById<TextView>(R.id.textView)?.text
            )
        }
        // Close the activity
        activityScenario.close()
    }
}

