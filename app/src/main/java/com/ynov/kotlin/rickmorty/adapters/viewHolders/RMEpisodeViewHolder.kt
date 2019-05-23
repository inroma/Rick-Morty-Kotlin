package com.ynov.kotlin.rickmorty.adapters.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R

class RMEpisodeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var episodeNameTextView: TextView
    var episodeNumberTextView: TextView

    init {
        episodeNameTextView = itemView.findViewById(R.id.activity_episode_name_text)
        episodeNumberTextView = itemView.findViewById(R.id.activity_episode_number_text)
    }
}