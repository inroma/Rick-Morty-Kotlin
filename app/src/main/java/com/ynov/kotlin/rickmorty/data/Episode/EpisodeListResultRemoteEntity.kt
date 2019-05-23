package com.ynov.kotlin.rickmorty.data.Episode

data class EpisodeListResultRemoteEntity(
    val info: Info,
    val results: List<EpisodeRemoteEntity>
)