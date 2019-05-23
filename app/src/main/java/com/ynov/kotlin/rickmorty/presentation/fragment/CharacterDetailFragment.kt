package com.ynov.kotlin.rickmorty.presentation.fragment

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.viewModel.CharacterDetailViewModel

class CharacterDetailFragment : Fragment() {
    private lateinit var viewModel: CharacterDetailViewModel

    private lateinit var characterImage: ImageView
    private lateinit var characterName: TextView
    private lateinit var characterGender: TextView
    private lateinit var characterStatus: TextView
    private lateinit var characterSpecies: TextView
    private lateinit var characterType: TextView
    private lateinit var characterOrigin: TextView
    private lateinit var characterLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWidgets()
        arguments?.getString("characterId")?.let {
            viewModel = CharacterDetailViewModel(it)

            viewModel.characterDetail.observe(this@CharacterDetailFragment, Observer {
                this@CharacterDetailFragment.bindWidgetData(it)
            })

            viewModel.errorLiveData.observe(this@CharacterDetailFragment, Observer {
                this@CharacterDetailFragment.showError(it.localizedMessage)
            })
        }
    }

    private fun showError(message: String) {
        view?.let {
            Snackbar
                .make(it, message, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun setUpWidgets() {
        view?.let {
            characterImage = it.findViewById(R.id.activity_detail_character_image)
            characterName = it.findViewById(R.id.activity_detail_name_text)
            characterGender = it.findViewById(R.id.activity_detail_gender_text)
            characterStatus= it.findViewById(R.id.activity_detail_status_text)
            characterSpecies= it.findViewById(R.id.activity_detail_species_text)
            characterType= it.findViewById(R.id.activity_detail_type_text)
            characterOrigin= it.findViewById(R.id.activity_detail_origin_text)
            characterLocation= it.findViewById(R.id.activity_detail_location_text)
        }
    }

    private fun bindWidgetData(character: RMCharacter) {
        Picasso.get()
            .load(character.image)
            .into(characterImage)
        characterName.text = character.name
        characterGender.text = character.gender
        characterStatus.text = character.status
        characterSpecies.text = character.species
        characterType.text = character.type
        characterOrigin.text = character.origin
        characterLocation.text = character.location
    }
}