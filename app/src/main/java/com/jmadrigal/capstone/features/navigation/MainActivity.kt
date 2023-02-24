package com.jmadrigal.capstone.features.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.databinding.ActivityMainBinding
import com.jmadrigal.capstone.features.books.view.fragment.BooksFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory : BooksFragmentFactory

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
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
                R.id.availableBooksFragment -> "Disponibles"
                R.id.bookDetailsFragment -> "Detalle"
                else -> ""
            }
        }
    }
}