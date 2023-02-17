package com.jmadrigal.capstone.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jmadrigal.capstone.databinding.ActivitySplashBinding
import com.jmadrigal.capstone.features.navigation.MainActivity
import com.jmadrigal.capstone.utils.Constants.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navToBooks()
    }

    private fun navToBooks() {
        lifecycleScope.launch {
            delay(SPLASH_DELAY)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}