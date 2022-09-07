package com.example.fruitdoc.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fruitdoc.model.Product


@Dao
interface ProductDao {

    @Query("Select * from product order by name ASC")
    fun allProducts(): LiveData<List<Product>>

    @Query("SELECT * from product WHERE problem_id = :problemId order by name ASC")
    fun productsByProblemId(problemId: Int): List<Product>
}