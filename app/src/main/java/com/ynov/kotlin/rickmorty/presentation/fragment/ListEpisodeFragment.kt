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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ynov.kotlin.rickmorty.presentation.List.adapter.EpisodeListAdapter
import com.ynov.kotlin.rickmorty.presentation.viewModel.EpisodeListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListEpisodeFragment: Fragment(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {

    }

    lateinit var episodeViewModel: EpisodeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val episodeListAdapter = EpisodeListAdapter()
        fragment_list_recyclerview.adapter = episodeListAdapter
        fragment_list_recyclerview.layoutManager = LinearLayoutManager(context)

        episodeViewModel = ViewModelProviders.of(this).get(EpisodeListViewModel::class.java)

        episodeViewModel.episodeListLiveData.observe(this, Observer { episodeListAdapter.updateList(it) })

        fun onChanged(throwable: Throwable) {
            if(getView() != null)
                Snackbar.make(view, throwable.localizedMessage, Snackbar.LENGTH_LONG)
        }

        my_swipeRefresh_Layout.setOnRefreshListener {
            episodeViewModel.RefreshEpisodeList()
            my_swipeRefresh_Layout.isRefreshing = false
        }
    }


}