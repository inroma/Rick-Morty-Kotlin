package com.ynov.kotlin.rickmorty.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.local.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel(id: String): ViewModel()  {
    val characterDetail: MutableLiveData<RMCharacter> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        loadCharactersList(id)
    }

    private fun loadCharactersList(id: String) {
        RMApplication
            .app
            .dataRepository
            .retrieveCharacterDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                characterDetail.postValue(it)
            }, {
                errorLiveData.postValue(it)
            })
    }
}