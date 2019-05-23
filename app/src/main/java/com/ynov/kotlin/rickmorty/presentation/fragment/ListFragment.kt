package com.ynov.kotlin.rickmorty.presentation.fragment

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
import com.ynov.kotlin.rickmorty.presentation.List.adapter.CharacterListAdapter
import com.ynov.kotlin.rickmorty.presentation.viewModel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_list.my_swipeRefresh_Layout

class ListFragment: Fragment(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {

    }

    lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterListAdapter = CharacterListAdapter()
        fragment_list_recyclerview.adapter = characterListAdapter
        fragment_list_recyclerview.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)

        viewModel.characterListLiveData.observe(this, Observer { characterListAdapter.updateList(it) })

        fun onChanged(throwable: Throwable) {
            if(getView() != null)
                Snackbar.make(view, throwable.localizedMessage, Snackbar.LENGTH_LONG)
        }

        my_swipeRefresh_Layout.setOnRefreshListener {
            viewModel.RefreshList()
            my_swipeRefresh_Layout.isRefreshing = false
        }

        fun add_characters(number: Int) {
            viewModel.characterListLiveData.observe(this, Observer { characterListAdapter.appendList(it) })
        }
    }
}