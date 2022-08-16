package com.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.navigationIcon = getDrawable(R.drawable.ic_actionbar_menu)
        NavigationUI.setupActionBarWithNavController(
            this,
            findNavController(R.id.nav_host_fragment_activity_home_screen)
        )
        binding.toolbar.setNavigationOnClickListener {
            findNavController(R.id.nav_host_fragment_activity_home_screen).navigateUp()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            title = when (destination.id) {
                R.id.newsDetailFragment -> getString(R.string.toolbar_main_details)
                else -> getString(R.string.toolbar_main_title)
            }
        }

    }
}