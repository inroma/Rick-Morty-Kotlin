package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.data.entity.remote.RemoteCharacter
import com.ynov.kotlin.rickmorty.data.entity.remote.RemoteCharactersListResult
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager {

    private val service: ApiService

    interface ApiService {
        @GET("api/character")
        fun retrieveCharactersList(): io.reactivex.Single<RemoteCharactersListResult>

        @GET("api/character/{id}")
        fun retrieveCharacterDetails(@Path("id") id: String): io.reactivex.Single<RemoteCharacter>
    }

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun retrieveCharactersList(): io.reactivex.Single<List<RemoteCharacter>> =
        service.retrieveCharactersList().map {
            it.remoteCharactersList
        }

    fun retrieveCharacterDetails(id: String): io.reactivex.Single<RemoteCharacter> =
            service.retrieveCharacterDetails(id)
}