package com.farizhustha.makanansriwijaya.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farizhustha.makanansriwijaya.di.Injection
import com.farizhustha.makanansriwijaya.ui.screen.detail.DetailViewModel
import com.farizhustha.makanansriwijaya.ui.screen.home.HomeViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(Injection.provideRepository()) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(Injection.provideRepository()) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}