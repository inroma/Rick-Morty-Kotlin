package com.ynov.kotlin.rickmorty.presentation.viewModel

import android.content.Intent
import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.Entity.CharacterRemoteEntity
import com.ynov.kotlin.rickmorty.presentation.CharacterActivity
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterViewModel: ViewModel() {
    var characterLiveData: MutableLiveData<CharacterRemoteEntity> = MutableLiveData()

    fun retrieveCharacter(id: Int){
        RMApplication.app.dataRepo.retrieveCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                })
    }
}
