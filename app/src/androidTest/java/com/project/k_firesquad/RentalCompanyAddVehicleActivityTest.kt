package com.project.k_firesquad

import android.content.Intent
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RentalCompanyAddVehicleActivityTest {
    @Test
    fun testTvHeaderAddNewVehiclePage(){
        //start activity
       val intent= Intent(ApplicationProvider.getApplicationContext(),MainActivityProfile::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val activityScenario= ActivityScenario.launch<MainActivityProfile>(intent)


        //find and click the add new vehicle button
        onView(withId(R.id.btnAddVehicle)).perform(click())

        //check that the header is correct
        onView(withId(R.id.TvHeaderAddNewVehicle)).check{view,_->
            assertEquals("Add New Vehicle",view?.findViewById<TextView>(R.id.TvHeaderAddNewVehicle)?.text)

        }

       //close activity
        activityScenario.close()
    }


}