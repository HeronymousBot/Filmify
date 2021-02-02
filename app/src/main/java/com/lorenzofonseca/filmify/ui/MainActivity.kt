package com.lorenzofonseca.filmify.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.lorenzofonseca.filmify.R

class MainActivity : AppCompatActivity() {
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_filmify) as NavHostFragment).navController
    }

    private val navGraph by lazy {
        navController.navInflater.inflate(R.navigation.nav_graph)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController.graph = navGraph
    }
}