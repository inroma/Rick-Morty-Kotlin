package com.ynov.kotlin.rickmorty.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.Episode.EpisodeRemoteEntity
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class EpisodeListViewModel: ViewModel() {
    var episodeListLiveData: MutableLiveData<List<EpisodeRemoteEntity>> = MutableLiveData()

    init{
        RefreshEpisodeList()
    }

    fun RefreshEpisodeList() {
        RMApplication.app.dataRepo
            .retrieveEpisodeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    episodeListLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                })
    }
}
