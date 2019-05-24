package com.ynov.kotlin.rickmorty.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    var page: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = "Episodes"
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

        fun add_characters() {
            episodeViewModel.episodeListLiveData.observe(this, Observer { episodeListAdapter.appendList(it) })
        }

        fragment_list_recyclerview.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx:
            Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    onScrolledToBottom()
                }
                else if(!recyclerView.canScrollVertically(-1)) {
                    onScrolledToTop()
                }
            }
            fun onScrolledToTop() {
                if(page > 1) {
                    page -= 1
                    val initialSize = episodeListAdapter.episodeList.size
                    episodeViewModel.RefreshEpisodePage(page)
                    add_characters()
                    val updatedSize = episodeListAdapter.episodeList.size
                    episodeListAdapter.notifyItemRangeInserted(initialSize,updatedSize)
                }
            }
            fun onScrolledToBottom() {
                val initialSize = episodeListAdapter.episodeList.size
                page += 1
                episodeViewModel.RefreshEpisodePage(page)
                add_characters()
                val updatedSize = episodeListAdapter.episodeList.size
                episodeListAdapter.notifyItemRangeInserted(initialSize,updatedSize)
                fragment_list_recyclerview.scrollToPosition(updatedSize)
            }
        })
    }


}