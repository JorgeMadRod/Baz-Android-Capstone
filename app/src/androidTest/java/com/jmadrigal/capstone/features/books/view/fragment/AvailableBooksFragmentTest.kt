package com.jmadrigal.capstone.features.books.view.fragment

class AvailableBooksFragmentTest() {}
/*
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jmadrigal.capstone.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.anything
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
/*
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AvailableBooksFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationToDetail() {

        val mockNavController = mock(NavController::class.java)

        val first = launchFragmentInContainer<AvailableBooksFragment>()

        first.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        onData(anything()).inAdapterView(withId(R.id.recycler)).atPosition(0).perform(click())
        verify(mockNavController).navigate(R.id.actionNavToDetail)
    }
}*/