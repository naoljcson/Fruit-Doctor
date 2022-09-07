package com.example.fruitdoc.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fruitdoc.model.Fruit

@Dao
interface FruitDao {
    // adds a new entry to our database.
    // if some data is same/conflict, it'll be replace with new data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertFruit(fruit: Fruit)

    // deletes an Fruit
    @Delete
     suspend fun deleteFruit(fruit: Fruit)

    // updates an Fruit.
    @Update
     fun updateFruit(fruit: Fruit)

    // read all the Fruit from eventTable
    // and arrange Fruit in ascending order
    // of their ids
    @Query("Select * from fruit order by name ASC")
    fun getAllFruits(): LiveData<List<Fruit>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines

    // delete all Fruit
    @Query("DELETE FROM fruit")
    fun clearFruit()
//
//    //you can use this too, to delete an Fruit by id.
    @Query("DELETE FROM fruit WHERE id = :id")
     fun deleteFruitById(id: Int)
}