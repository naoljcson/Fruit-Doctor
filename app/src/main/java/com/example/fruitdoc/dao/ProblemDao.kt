package com.example.fruitdoc.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.fruitdoc.model.Problem
import com.example.fruitdoc.model.Fruit


@Dao
interface ProblemDao {

    @Query("Select * from problem order by name ASC")
    fun allProblems(): LiveData<List<Problem>>

    @Query("SELECT * from problem WHERE fruit_id = :fruitId AND type = :type order by name ASC")
    suspend fun problemsByFruitId(fruitId: Int,type: String): List<Problem>

    @Query("SELECT * from problem WHERE id = :problemId ")
    fun problemsById(problemId: Int): Problem

    @Query("SELECT * from fruit WHERE id = :fruitId ")
    fun fruitByProblemId(fruitId: Int): Fruit

}