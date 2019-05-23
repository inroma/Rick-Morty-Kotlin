package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter

class DataRepository (
    private val apiManager: ApiManager
) {
    fun retriveCharacterslist(): io.reactivex.Single<List<RMCharacter>> =
        apiManager.retrieveCharactersList().map {
            it.map { remoteCharacter ->
                RMCharacter(
                    remoteCharacter.id,
                    remoteCharacter.name,
                    remoteCharacter.status,
                    remoteCharacter.species,
                    remoteCharacter.type,
                    remoteCharacter.gender,
                    remoteCharacter.origin.name,
                    remoteCharacter.location.name,
                    remoteCharacter.image,
                    remoteCharacter.created
                )
            }
        }

    fun retrieveCharacterDetails(id: String): io.reactivex.Single<RMCharacter> =
            apiManager.retrieveCharacterDetails(id).map {
                RMCharacter(
                    it.id,
                    it.name,
                    it.status,
                    it.species,
                    it.type,
                    it.gender,
                    it.origin.name,
                    it.location.name,
                    it.image,
                    it.created
                )
            }
}