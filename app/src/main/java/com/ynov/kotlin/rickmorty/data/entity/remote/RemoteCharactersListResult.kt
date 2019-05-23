package com.ynov.kotlin.rickmorty.data.entity.remote

import com.google.gson.annotations.SerializedName

class RemoteCharactersListResult {
    @SerializedName("results") val remoteCharactersList: List<RemoteCharacter> = emptyList()
}