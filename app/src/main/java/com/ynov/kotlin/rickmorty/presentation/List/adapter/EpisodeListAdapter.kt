package com.ynov.kotlin.rickmorty.presentation.List.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.Episode.EpisodeRemoteEntity
import kotlinx.android.synthetic.main.view_episode_list_item.view.*

class EpisodeListAdapter: RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {

    var episodeList: MutableList<EpisodeRemoteEntity> = mutableListOf()

    override fun getItemCount(): Int {
        return episodeList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_episode_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.episodeList[position])
    }

    class ViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(episode: EpisodeRemoteEntity) {
            itemView.nameTextView.text = episode.name
            itemView.episodeTextView.text = episode.episode
            itemView.air_dateTextView.text = episode.air_date
        }
    }

    fun updateList(episodeList: List<EpisodeRemoteEntity> ) {
        this.episodeList.clear()
        this.episodeList.addAll(episodeList)
        notifyDataSetChanged()
    }
}