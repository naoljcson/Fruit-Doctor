package com.example.fruitdoc.repository

import androidx.lifecycle.LiveData
import com.example.fruitdoc.dao.ProblemDao
import com.example.fruitdoc.model.Problem
import com.example.fruitdoc.model.Fruit

class ProblemRepository(private val problemDao: ProblemDao) {
    // get all the problems
    fun allProblems(): LiveData<List<Problem>> = problemDao.allProblems()

    // get   problem by id
    fun problemsById(problemId: Int): Problem = problemDao.problemsById(problemId)

    // get Fruit by   problem  id
    fun fruitByProblemId(FruitId: Int): Fruit = problemDao.fruitByProblemId(FruitId)

    // get all the problem by Fruit id
    suspend fun problemsByFruitId(FruitId: Int,type: String): List<Problem> = problemDao.problemsByFruitId(FruitId,type)
}