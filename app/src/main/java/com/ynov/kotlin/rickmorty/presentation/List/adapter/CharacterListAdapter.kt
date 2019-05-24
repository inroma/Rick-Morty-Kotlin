package com.ynov.kotlin.rickmorty.presentation.List.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.Entity.CharacterRemoteEntity
import com.ynov.kotlin.rickmorty.presentation.CharacterActivity
import kotlinx.android.synthetic.main.view_character_list_item_name.view.*


class CharacterListAdapter: RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    var characterList: MutableList<CharacterRemoteEntity> = mutableListOf()

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_character_list_item_name, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.characterList[position])
    }

    class ViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(character: CharacterRemoteEntity) {
            itemView.nameTextView.text = character.name
            Picasso.get()
                .load(character.image)
                .resize(1000,1000)
                .centerCrop()
                .into(itemView.characterImage)
            itemView.genderTextView.text = character.gender
            itemView.originTextView.text = character.origin.name
            val intent = Intent(itemView.context, CharacterActivity::class.java)
            intent.putExtra("characterId", character.id)
            itemView.setOnClickListener{itemView.context.startActivity(intent)}
        }
    }

    fun updateList(characterList: List<CharacterRemoteEntity> ) {
        this.characterList.clear()
        this.characterList.addAll(characterList)
        notifyDataSetChanged()
    }

    fun appendList(charList: List<CharacterRemoteEntity> ) {
        if(this.characterList.contains(charList[1])) {
            this.characterList.addAll(charList)
            notifyDataSetChanged()
        }
    }
}