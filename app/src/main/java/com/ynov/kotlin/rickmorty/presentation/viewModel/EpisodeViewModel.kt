package com.ynov.kotlin.rickmorty.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.Episode.EpisodeRemoteEntity
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class EpisodeViewModel: ViewModel() {
    var episodeLiveData: MutableLiveData<EpisodeRemoteEntity> = MutableLiveData()

    fun retrieveEpisode(id: Int){
        RMApplication.app.dataRepo.retrieveEpisode(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    episodeLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                })
    }
}
