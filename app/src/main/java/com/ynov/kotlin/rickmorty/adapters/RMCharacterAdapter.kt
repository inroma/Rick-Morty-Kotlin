package com.ynov.kotlin.rickmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.adapters.viewHolders.RMCharacterViewHolder
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter

class RMCharacterAdapter(private val characters: List<RMCharacter>): RecyclerView.Adapter<RMCharacterViewHolder>() {

    lateinit var onClick: (String) -> Unit
    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: RMCharacterViewHolder, position: Int) {
        Picasso.get()
            .load(characters.get(position).image)
            //.placeholder(R.drawable.placeholder_image)
            //.error(R.drawable.error_image)
            .into(holder.characterImageView)

        holder.characterGenderTextView.text = characters.get(position).gender
        holder.characterNameTextView.text= characters.get(position).name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMCharacterViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_layout, parent, false)
        val viewHolder = RMCharacterViewHolder(v)
        v.setOnClickListener {
            onClick(characters.get(viewHolder.adapterPosition).id.toString())
        }
        return viewHolder
    }
}