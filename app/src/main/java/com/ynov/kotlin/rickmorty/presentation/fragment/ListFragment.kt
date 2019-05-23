package com.ynov.kotlin.rickmorty.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    var page: Int = 1

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
            viewModel.RefreshList(1)
            my_swipeRefresh_Layout.isRefreshing = false
        }

        fun add_characters() {
            viewModel.characterListLiveData.observe(this, Observer { characterListAdapter.appendList(it) })
        }

        fragment_list_recyclerview.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx:
            Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    onScrolledToBottom()
                }
            }
            fun onScrolledToBottom() {
                val initialSize = characterListAdapter.characterList.size
                viewModel.RefreshList(page)
                add_characters()
                page += 1
                val updatedSize = characterListAdapter.characterList.size
                characterListAdapter.notifyItemRangeInserted(initialSize,updatedSize)
            }
        })
    }
}