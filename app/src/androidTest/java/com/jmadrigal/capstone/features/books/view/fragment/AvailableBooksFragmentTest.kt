package com.jmadrigal.capstone.features.books.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.jmadrigal.capstone.features.books.view.adapter.AvailableBooksAdapter
import com.jmadrigal.capstone.launchFragmentInHiltContainer
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.features.books.repository.FakeBooksRepository
import com.jmadrigal.capstone.features.books.viewmodel.BooksViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
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
    fun testNavigationToDetail() {

        val testViewModel = BooksViewModel(FakeBooksRepository())
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AvailableBooksFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
            //viewModel = testViewModel
        }

        Espresso.onView(ViewMatchers.withId(R.id.rvAvailableBooks)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AvailableBooksAdapter.ViewHolder>(0, click())
        )

        Mockito.verify(navController).popBackStack()

    }
}