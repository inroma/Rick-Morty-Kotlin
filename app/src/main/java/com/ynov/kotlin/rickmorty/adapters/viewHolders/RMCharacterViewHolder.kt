package com.ynov.kotlin.rickmorty.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ynov.kotlin.rickmorty.R
import androidx.recyclerview.widget.RecyclerView

class RMCharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var characterImageView: ImageView
    var characterNameTextView: TextView
    var characterGenderTextView: TextView

    init {
        characterImageView = itemView.findViewById(R.id.mainactivity_character_image)
        characterNameTextView = itemView.findViewById(R.id.mainactivity_name_text)
        characterGenderTextView = itemView.findViewById(R.id.mainactivity_gender_text)
    }
}