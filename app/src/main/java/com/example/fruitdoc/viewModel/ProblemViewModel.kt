package com.example.fruitdoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fruitdoc.database.FruitDoctorDatabase
import com.example.fruitdoc.model.Problem
import com.example.fruitdoc.model.Fruit
import com.example.fruitdoc.repository.ProblemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ProblemViewModel( application: Application) : AndroidViewModel(application) {
    val allProblems: LiveData<List<Problem>>
    val repository: ProblemRepository

    init {
        val dao = FruitDoctorDatabase.getDatabase(application).problemDao()
        repository = ProblemRepository(dao)
        allProblems = repository.allProblems()
    }

     suspend fun allProblemsByFruitId(fruitId: Int,type: String): List<Problem> {
         var problems: List<Problem>
         withContext(viewModelScope.coroutineContext + Dispatchers.IO) {  problems = repository.problemsByFruitId(fruitId,type)  }
        return problems
    }

    suspend fun problemsById(id: Int): Problem {
        var problem: Problem
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {  problem = repository.problemsById(id)  }
        return problem
      }


    suspend fun fruitByProblemId(id: Int): Fruit {
        var fruit: Fruit
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {  fruit = repository.fruitByProblemId(id)  }
        return fruit
    }
    }





