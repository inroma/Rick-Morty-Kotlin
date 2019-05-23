package com.ynov.kotlin.rickmorty.presentation

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.fragment.ListEpisodeFragment
import com.ynov.kotlin.rickmorty.presentation.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, ListFragment()).commit()

        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_characters -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container, ListFragment()).commit()
                    navigationView.itemTextColor
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_episodes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container, ListEpisodeFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container, ListFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Quitter")
            .setMessage("Voulez-vous vraiment quitter ?")
            .setPositiveButton("Oui",
                DialogInterface.OnClickListener{ dialog, _ ->
                    dialog.dismiss()
                    finish()
                })
            .setNegativeButton("Non", DialogInterface.OnClickListener{ dialog, _ ->
                dialog.dismiss()
                })
            .show()
    }
}
