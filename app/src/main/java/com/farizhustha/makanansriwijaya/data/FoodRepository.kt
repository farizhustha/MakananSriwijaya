package com.farizhustha.makanansriwijaya.data

import com.farizhustha.makanansriwijaya.model.Food
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class FoodRepository private constructor(
    private val db: FirebaseFirestore,
    private val storageRef: StorageReference
) {
    private var listFood = mutableListOf<Food>()

    fun getAllFood(): Flow<List<Food>> {
        return getFoodFromFirebase()
    }

    fun getFoodById(id: String): Food {
        return listFood.first {
            it.id == id
        }
    }

    fun searchFood(query: String): List<Food> {
        return listFood.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.origin.contains(query, ignoreCase = true)
        }
    }

    private fun getFoodFromFirebase(): Flow<List<Food>> = flow {
        val newListFood = ArrayList<Food>()

        withContext(Dispatchers.IO) {
            val querySnapshot = db.collection("makanan").get().await()
            val deferredList = querySnapshot.documents.map { document ->

                async {
                    val name: String = document.data?.get("nama").toString()
                    val origin: String = document.data?.get("asal").toString()
                    val description: String = document.data?.get("deskripsi").toString()
                    val imagePath: String = document.data?.get("image").toString()
                    val listResult = storageRef.child(imagePath).list(1).await()

                    val url = listResult.items.first().downloadUrl.await()

                    val data = Food(
                        document.id,
                        name,
                        origin,
                        url.toString(),
                        description
                    )
                    data
                }
            }
            deferredList.awaitAll().forEach { food ->
                newListFood.add(food)
            }
        }
        listFood = newListFood
        emit(newListFood)
    }

    companion object {
        @Volatile
        private var instance: FoodRepository? = null

        fun getInstance(db: FirebaseFirestore, storageRef: StorageReference): FoodRepository =
            instance ?: synchronized(this) {
                FoodRepository(db, storageRef).apply {
                    instance = this
                }
            }
    }
}