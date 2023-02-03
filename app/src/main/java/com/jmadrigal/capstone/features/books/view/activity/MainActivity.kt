package com.jmadrigal.capstone.features.books.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.databinding.ActivityMainBinding
import com.jmadrigal.capstone.features.books.view.fragment.AvailableBooksFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addFragment(AvailableBooksFragment())

       /* onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                when (fragment) {
                    null, is AvailableBooksFragment -> finish()
                    else -> supportFragmentManager.popBackStack()
                }

                if (isEnabled) {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })*/
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment is AvailableBooksFragment){
            finish()
        } else{
            super.onBackPressed()
        }
    }

}