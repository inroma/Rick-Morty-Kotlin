package com.ynov.kotlin.rickmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.fragment.CharacterFragment

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.character_activity_fragment_container, CharacterFragment(intent.getIntExtra("characterId", 1))).commit()
    }
}
