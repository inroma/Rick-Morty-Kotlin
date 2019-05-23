package com.ynov.kotlin.rickmorty.data.entity.remote

import com.google.gson.annotations.SerializedName

class RemoteEpisodeListResult {
    @SerializedName("results") val remoteEpisodeList: List<RemoteEpisode> = emptyList()
}