package com.ynov.kotlin.rickmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.adapters.viewHolders.RMEpisodeViewHolder
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode

class RMEpisodeAdapter(private val episode: List<RMEpisode>): RecyclerView.Adapter<RMEpisodeViewHolder>() {

    //lateinit var onClick: (String) -> Unit
    override fun getItemCount(): Int = episode.size

    override fun onBindViewHolder(holder: RMEpisodeViewHolder, position: Int) {
        holder.episodeNameTextView.text = episode.get(position).name
        holder.episodeNumberTextView.text= episode.get(position).episode
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMEpisodeViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_layout, parent, false)
        val viewHolder = RMEpisodeViewHolder(v)
        /*
        v.setOnClickListener {
            onClick(epsiode.get(viewHolder.adapterPosition).id.toString())
        }
        */
        return viewHolder
    }
}