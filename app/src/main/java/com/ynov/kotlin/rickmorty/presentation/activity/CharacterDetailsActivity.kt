package com.ynov.kotlin.rickmorty.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.fragment.CharacterDetailFragment

class CharacterDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val bundle = Bundle()
        bundle.putString("characterId", intent.getStringExtra("characterId"))
        val characterDetailFragment = CharacterDetailFragment()
        characterDetailFragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_detail_fragment_container, characterDetailFragment)
            .commit()
    }
}