package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.fragment.CharactersFragment

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_caracteres -> {
                fragmentCharactersList()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_episodes -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainactivity_fragment_container, CharactersFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if( savedInstanceState == null) {
            fragmentCharactersList()
        }
    }

    private fun fragmentCharactersList() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainactivity_fragment_container, CharactersFragment())
            .commit()
    }

    private fun fragmentEpisodeList() {

    }
}
