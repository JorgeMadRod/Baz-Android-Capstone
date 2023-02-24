package com.jmadrigal.capstone.features.books.view.fragment

import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.core.models.AvailableBooksResponse
import com.jmadrigal.capstone.features.books.view.adapter.AvailableBooksAdapter
import com.jmadrigal.capstone.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class AvailableBooksFragmentTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationToDetail() {

       /* val mockNavController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AvailableBooksFragment>()/*.onFragment { fragment ->
            Log.v("===", "Antes")
            Assert.assertNotNull(fragment.activity)
            Log.v("===", "Despues")
            mockNavController.setGraph(R.navigation.capstone_navigation)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }*/
        /*val first = launchFragmentInContainer<AvailableBooksFragment>()

        first.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }*/

        val response = AvailableBooksResponse(true, ArrayList<AvailableBook>())

        onData(anything()).inAdapterView(withId(R.id.rvAvailableBooks))
            .perform(RecyclerViewActions.scrollToLastPosition<AvailableBooksAdapter.ViewHolder>())*/

        /*onView(withId(R.id.rvAvailableBooks))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<AvailableBooksAdapter.ViewHolder>(0, click()))*
        //onData(anything()).inAdapterView(withId(R.id.rvAvailableBooks))//.atPosition(0).perform(click())
        /*verify(mockNavController).navigate(R.id.actionNavToDetail)
        Assert.assertEquals(mockNavController.currentDestination?.id, R.id.bookDetailsFragment)*/

        mockNavController.setGraph(R.navigation.capstone_navigation)*/
    }
}