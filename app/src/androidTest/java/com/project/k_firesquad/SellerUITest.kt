package com.project.k_firesquad

import android.content.Intent
import android.widget.TextView

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.project.k_firesquad.activites.SellerProfileActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SellerProductsListActivityTest {

    @Test
    fun testTvHeaderSellerMarketPlace() {
        // Start the activity
        val intent = Intent(ApplicationProvider.getApplicationContext(), SellerProfileActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val activityScenario = ActivityScenario.launch<SellerProfileActivity>(intent)

        // Find and click the marketPlace button
        onView(withId(R.id.marketPlace)).perform(click())

        // Check that the text of tvHeader in the next screen is "Seller MarketPlace"
        onView(withId(R.id.tvHeader)).check { view, _ ->
            assertEquals("Farmer MarketPlace", view?.findViewById<TextView>(R.id.tvHeader)?.text)
        }

        // Close the activity
        activityScenario.close()
    }


    @Test
    fun testTvHeaderSellerMyList() {
        // Start the activity
        val intent = Intent(ApplicationProvider.getApplicationContext(), SellerProfileActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val activityScenario = ActivityScenario.launch<SellerProfileActivity>(intent)


        // Find and click the marketPlace button
        onView(withId(R.id.viewSellerProducts)).perform(click())

        // Check that the text of tvHeader in the next screen is "Seller MarketPlace"
        onView(withId(R.id.tvHeader)).check { view, _ ->
            assertEquals("My List", view?.findViewById<TextView>(R.id.tvHeader)?.text)
        }

        // Close the activity
        activityScenario.close()
    }
}
