package com.ynov.kotlin.rickmorty.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.viewModel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character.*
import kotlinx.android.synthetic.main.fragment_character.characterImage
import kotlinx.android.synthetic.main.view_character_list_item_name.*

class CharacterFragment(id: Int): Fragment() {
    lateinit var viewModel: CharacterViewModel
    var charId: Int = id

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        viewModel.retrieveCharacter(this.charId)
        viewModel.characterLiveData.observe(this, Observer {
            this.characterNameTextView.text = it.name
            Picasso.get()
                .load(it.image)
                .resize(1000,1000)
                .centerCrop()
                .into(this.characterImage)
            this.characterGenderTextView.text = it.gender
            this.characterStatusTextView.text = it.status
            this.characterSpeciesTextView.text = it.species
            this.characterTypeTextView.text = it.type
            this.characterOriginTextView.text = it.origin.name
            this.characterLocationTextView.text = it.location.name
        })

        fun onChanged(throwable: Throwable) {
            if(getView() != null)
                Snackbar.make(view, throwable.localizedMessage, Snackbar.LENGTH_LONG)
        }
    }


}