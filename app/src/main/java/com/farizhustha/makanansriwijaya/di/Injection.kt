package com.farizhustha.makanansriwijaya.di

import com.farizhustha.makanansriwijaya.data.FoodRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

object Injection {
    fun provideRepository(): FoodRepository {
        val db = Firebase.firestore
        val storage = Firebase.storage
        val storageRef = storage.reference
        return FoodRepository.getInstance(db, storageRef)
    }
}