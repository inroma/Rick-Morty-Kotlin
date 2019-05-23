package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.Entity.CharacterRemoteEntity
import com.ynov.kotlin.rickmorty.data.Episode.EpisodeRemoteEntity

class CacheManager {
    var cacheList: List<CharacterRemoteEntity> = emptyList()
    var cacheEpisodeList: List<EpisodeRemoteEntity> = emptyList()
}