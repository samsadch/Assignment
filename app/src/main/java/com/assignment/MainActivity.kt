package com.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}