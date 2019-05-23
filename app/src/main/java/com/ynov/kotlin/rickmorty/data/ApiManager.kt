package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.Entity.CharacterListResultRemoteEntity
import com.ynov.kotlin.rickmorty.data.Entity.CharacterRemoteEntity
import com.ynov.kotlin.rickmorty.data.Episode.EpisodeListResultRemoteEntity
import com.ynov.kotlin.rickmorty.data.Episode.EpisodeRemoteEntity
import io.reactivex.Single
import retrofit2.Call
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
        fun retrieveCharacterList(): Single<CharacterListResultRemoteEntity>

        @GET("api/character/{id}")
        fun retrieveCharacter(@Path("id", encoded=true) id :Int): Single<CharacterRemoteEntity>

        @GET("api/episode")
        fun retrieveEpisodeList(): Single<EpisodeListResultRemoteEntity>

        @GET("api/episode/{id}")
        fun retrieveEpisode(@Path("id", encoded=true) id :Int): Single<EpisodeRemoteEntity>

    }

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun retrieveCharacterList() = service.retrieveCharacterList()
    fun retrieveCharacter(id: Int) = service.retrieveCharacter(id)
    fun retrieveEpisodeList() = service.retrieveEpisodeList()
    fun retrieveEpisode(id: Int) = service.retrieveEpisode(id)
}