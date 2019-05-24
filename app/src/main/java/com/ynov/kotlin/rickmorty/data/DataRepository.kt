package com.ynov.kotlin.rickmorty.data

import android.util.Log
import com.ynov.kotlin.rickmorty.data.Entity.CharacterRemoteEntity
import com.ynov.kotlin.rickmorty.data.Episode.EpisodeRemoteEntity
import io.reactivex.Single

class DataRepository(private val apiManager: ApiManager) {
    var cacheManager = CacheManager()

    fun retrieveCharacterList(): Single<List<CharacterRemoteEntity>> {
        return Single.defer<List<CharacterRemoteEntity>>{
            if(cacheManager.cacheList.isEmpty()){
                apiManager.retrieveCharacterList().map {
                    return@map it.results.map{ characterRemoteEntity: CharacterRemoteEntity ->
                        CharacterRemoteEntity(
                            characterRemoteEntity.created, characterRemoteEntity.episode, characterRemoteEntity.gender,
                            characterRemoteEntity.id, characterRemoteEntity.image, characterRemoteEntity.location,
                            characterRemoteEntity.name, characterRemoteEntity.origin, characterRemoteEntity.species,
                            characterRemoteEntity.status, characterRemoteEntity.type, characterRemoteEntity.url)
                    }
                }.doAfterSuccess{
                    cacheManager.cacheList = it
                }
            }
            else {
                Single.just(cacheManager.cacheList)
            }
        }
    }

    fun retrieveCharacterPage(number: Int): Single<List<CharacterRemoteEntity>> {
        return Single.defer<List<CharacterRemoteEntity>>{
            if(cacheManager.cacheList.isEmpty()){
                apiManager.retrieveCharacterList().map {
                    return@map it.results.map{ characterRemoteEntity: CharacterRemoteEntity ->
                        CharacterRemoteEntity(
                            characterRemoteEntity.created, characterRemoteEntity.episode, characterRemoteEntity.gender,
                            characterRemoteEntity.id, characterRemoteEntity.image, characterRemoteEntity.location,
                            characterRemoteEntity.name, characterRemoteEntity.origin, characterRemoteEntity.species,
                            characterRemoteEntity.status, characterRemoteEntity.type, characterRemoteEntity.url)
                    }
                }.doAfterSuccess{
                    cacheManager.cacheList = it
                }
            }
            else if(number != 1){
                apiManager.retrieveCharacterPage(number).map {
                    return@map it.results.map{ characterRemoteEntity: CharacterRemoteEntity ->
                        CharacterRemoteEntity(
                            characterRemoteEntity.created, characterRemoteEntity.episode, characterRemoteEntity.gender,
                            characterRemoteEntity.id, characterRemoteEntity.image, characterRemoteEntity.location,
                            characterRemoteEntity.name, characterRemoteEntity.origin, characterRemoteEntity.species,
                            characterRemoteEntity.status, characterRemoteEntity.type, characterRemoteEntity.url)
                    }
                }.doAfterSuccess{
                    cacheManager.cacheList = it
                }
            }
            else {
                Single.just(cacheManager.cacheList)
            }
        }
    }

    fun retrieveCharacter(id: Int): Single<CharacterRemoteEntity> =
        apiManager.retrieveCharacter(id).map{
            it
        }

    fun retrieveEpisodeList(): Single<List<EpisodeRemoteEntity>> {
        return Single.defer<List<EpisodeRemoteEntity>>{
            if(cacheManager.cacheEpisodeList.isEmpty()){
                apiManager.retrieveEpisodeList().map {
                    return@map it.results.map{ EpisodeRemoteEntity: EpisodeRemoteEntity ->
                        EpisodeRemoteEntity(
                            EpisodeRemoteEntity.air_date, EpisodeRemoteEntity.characters, EpisodeRemoteEntity.created,
                            EpisodeRemoteEntity.episode, EpisodeRemoteEntity.id, EpisodeRemoteEntity.name,
                            EpisodeRemoteEntity.url)
                    }
                }.doAfterSuccess{
                    cacheManager.cacheEpisodeList = it
                }
            }
            else {
                Single.just(cacheManager.cacheEpisodeList)
            }
        }
    }

    fun retrieveEpisodePage(number: Int): Single<List<EpisodeRemoteEntity>> {
        return Single.defer<List<EpisodeRemoteEntity>>{
            if(cacheManager.cacheList.isEmpty()){
                apiManager.retrieveEpisodeList().map {
                    return@map it.results.map{ EpisodeRemoteEntity: EpisodeRemoteEntity ->
                        EpisodeRemoteEntity(
                            EpisodeRemoteEntity.air_date, EpisodeRemoteEntity.characters, EpisodeRemoteEntity.created,
                            EpisodeRemoteEntity.episode, EpisodeRemoteEntity.id, EpisodeRemoteEntity.name,
                            EpisodeRemoteEntity.url)
                    }
                }.doAfterSuccess{
                    cacheManager.cacheEpisodeList = it
                }
            }
            else if(number != 1){
                apiManager.retrieveEpisodePage(number).map {
                    return@map it.results.map{ EpisodeRemoteEntity: EpisodeRemoteEntity ->
                        EpisodeRemoteEntity(
                            EpisodeRemoteEntity.air_date, EpisodeRemoteEntity.characters, EpisodeRemoteEntity.created,
                            EpisodeRemoteEntity.episode, EpisodeRemoteEntity.id, EpisodeRemoteEntity.name,
                            EpisodeRemoteEntity.url)
                    }
                }.doAfterSuccess{
                    cacheManager.cacheEpisodeList = it
                }
            }
            else {
                Single.just(cacheManager.cacheEpisodeList)
            }
        }
    }

    fun retrieveEpisode(id: Int): Single<EpisodeRemoteEntity> =
        apiManager.retrieveEpisode(id).map{
            it
        }
}