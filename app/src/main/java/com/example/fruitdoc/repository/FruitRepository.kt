package com.example.fruitdoc.repository

import androidx.lifecycle.LiveData
import com.example.fruitdoc.dao.FruitDao
import com.example.fruitdoc.model.Fruit

class FruitRepository(private val fruitDao: FruitDao) {
    // get all the events
    fun getAllFruits(): LiveData<List<Fruit>> = fruitDao.getAllFruits()

    // adds an event to our database.
    suspend fun insertEvent(fruit: Fruit) {
        fruitDao.insertFruit(fruit)
    }

    // deletes an event from database.
    suspend fun deleteFruit(fruit: Fruit) {
        fruitDao.deleteFruit(fruit)
    }

    // updates an event from database.
    suspend fun updateFruit(fruit: Fruit) {
        fruitDao.updateFruit(fruit)
    }

//    //delete an event by id.
    suspend fun deleteFruitById(id: Int) = fruitDao.deleteFruitById(id)
//
//    // delete all events
    suspend fun clearFruit() = fruitDao.clearFruit()
}
