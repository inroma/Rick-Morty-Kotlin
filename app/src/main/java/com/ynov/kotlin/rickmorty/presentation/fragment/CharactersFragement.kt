package com.ynov.kotlin.rickmorty.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.adapters.RMCharacterAdapter
import com.ynov.kotlin.rickmorty.presentation.activity.CharacterDetailsActivity
import com.ynov.kotlin.rickmorty.presentation.viewModel.CharactersListViewModel
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharactersListViewModel::class.java)

        characters_recycleview.apply {
            layoutManager = LinearLayoutManager(activity)

            viewModel.charactersList.observe(this@CharactersFragment, Observer {
                val charactersListAdapter = RMCharacterAdapter(it)
                charactersListAdapter.onClick = {
                    clickForDetails(it)
                }
                adapter = charactersListAdapter
            })

            viewModel.errorLiveData.observe(this@CharactersFragment, Observer {
                this@CharactersFragment.showError(it.localizedMessage)
            })
        }
    }

    private fun clickForDetails(id: String) {
        var intent = Intent(context, CharacterDetailsActivity::class.java)
        intent.putExtra("characterId", id)
        startActivity(intent)
    }

    private fun showError(message: String) {
        view?.let {
            Snackbar
                .make(it, message, Snackbar.LENGTH_LONG)
                .show()
        }
    }

}