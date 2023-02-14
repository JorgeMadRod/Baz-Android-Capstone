package com.jmadrigal.capstone.features.books.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
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
        setSupportActionBar(binding.toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment? ?: return

        appBarConfiguration = AppBarConfiguration(host.navController.graph)
    }
}