package com.ynov.kotlin.rickmorty.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.Entity.CharacterRemoteEntity
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel: ViewModel() {
    var characterListLiveData: MutableLiveData<List<CharacterRemoteEntity>> = MutableLiveData()

    init{
        RefreshList()
    }

    fun RefreshList() {
        RMApplication.app.dataRepo
            .retrieveCharacterList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterListLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                })
    }

    fun RefreshListPage(page: Int) {
        RMApplication.app.dataRepo
            .retrieveCharacterPage(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterListLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                })
    }
}
