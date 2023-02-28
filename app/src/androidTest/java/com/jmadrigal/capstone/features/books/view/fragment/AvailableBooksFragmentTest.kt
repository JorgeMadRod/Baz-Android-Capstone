package com.jmadrigal.capstone.features.books.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.di.RepositoryModule
import com.jmadrigal.capstone.features.books.view.adapter.AvailableBooksAdapter
import com.jmadrigal.capstone.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import javax.inject.Inject


@HiltAndroidTest
@UninstallModules(RepositoryModule::class)
@RunWith(AndroidJUnit4::class)
class AvailableBooksFragmentTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: BooksFragmentFactory

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun checkItems() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AvailableBooksFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.rvAvailableBooks)).check(matches(hasChildCount(3)))
    }

    @Test
    fun testActionNavToDetail() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AvailableBooksFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.rvAvailableBooks)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AvailableBooksAdapter.ViewHolder>(0, click())
        )

        val availableBook = AvailableBook("BTC_MXN", "", "", "", "", "", "")
        Mockito.verify(navController).navigate(AvailableBooksFragmentDirections.actionNavToDetail(availableBook))
    }
}