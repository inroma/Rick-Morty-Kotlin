package com.ynov.kotlin.rickmorty.data

import android.util.Log
import com.ynov.kotlin.rickmorty.data.Entity.CharacterRemoteEntity
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

    fun retrieveCharacter(id: Int): Single<CharacterRemoteEntity> =
        apiManager.retrieveCharacter(id).map{
            it
        }
}