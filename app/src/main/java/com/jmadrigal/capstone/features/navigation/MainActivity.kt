package com.jmadrigal.capstone.features.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment? ?: return

        toolbar.setupWithNavController(host.navController)
        toolbar.title = null

        appBarConfiguration = AppBarConfiguration(host.navController.graph)

        host.navController.addOnDestinationChangedListener { _, destination, args ->
            title = when (destination.id) {
                R.id.availableBooksFragment -> getString(R.string.nav_available)
                R.id.bookDetailsFragment -> getString(R.string.nav_details)
                else -> getString(R.string.nav_empty)
            }
        }
    }
}