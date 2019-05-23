package com.ynov.kotlin.rickmorty.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.local.RMEpisode
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EpisodeListViewModel: ViewModel() {
    val episodeList: MutableLiveData<List<RMEpisode>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        loadCharactersList()
    }

    private fun loadCharactersList() {
        RMApplication
            .app
            .dataRepository
            .retriveEpisodeLsit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                episodeList.postValue(it)
            }, {
                errorLiveData.postValue(it)
            })
    }
}