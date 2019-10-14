package com.alexvedenyapin.revoluttestapp.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexvedenyapin.revoluttestapp.screen.main.domain.MainInteractor

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class MainViewModelFactory(
    private val interactor: MainInteractor
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(interactor) as T
}