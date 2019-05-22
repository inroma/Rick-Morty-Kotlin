package com.ynov.kotlin.rickmorty.presentation

import android.app.Application
import android.os.Handler
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.DataRepository

class RMApplication: Application() {

    companion object {
        lateinit var app: RMApplication
            private set
    }

    var dataRepo: DataRepository = DataRepository(ApiManager())

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}