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
import retrofit2.http.Query

private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager {

    private val service: ApiService

    interface ApiService {
        @GET("api/character")
        fun retrieveCharacterList(): Single<CharacterListResultRemoteEntity>

        @GET("api/character/{id}")
        fun retrieveCharacter(@Path("id", encoded=true) id :Int): Single<CharacterRemoteEntity>

        @GET("api/character")
        fun retrieveCharacterPage(@Query("page", encoded=true) id :Int): Single<CharacterListResultRemoteEntity>

        @GET("api/episode")
        fun retrieveEpisodeList(): Single<EpisodeListResultRemoteEntity>

        @GET("api/episode/{id}")
        fun retrieveEpisode(@Path("id", encoded=true) id :Int): Single<EpisodeRemoteEntity>

        @GET("api/episode")
        fun retrieveEpisodePage(@Query("page", encoded=true) id :Int): Single<EpisodeListResultRemoteEntity>
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
    fun retrieveCharacterPage(number: Int) = service.retrieveCharacterPage(number)
    fun retrieveEpisodeList() = service.retrieveEpisodeList()
    fun retrieveEpisodePage(number: Int) = service.retrieveEpisodePage(number)
    fun retrieveEpisode(id: Int) = service.retrieveEpisode(id)
}