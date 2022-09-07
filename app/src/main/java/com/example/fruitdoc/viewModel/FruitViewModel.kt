package com.example.fruitdoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fruitdoc.database.FruitDoctorDatabase
import com.example.fruitdoc.model.Fruit
import com.example.fruitdoc.repository.FruitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FruitViewModel( application: Application) : AndroidViewModel(application) {

     val allEvents: LiveData<List<Fruit>>
     val repository: FruitRepository

    // initialize dao, repository and all events
    init {
        val dao = FruitDoctorDatabase.getDatabase(application).fruitDao()
        repository = FruitRepository(dao)
        allEvents = repository.getAllFruits()
    }

    fun insertFruit(fruit: Fruit) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertEvent(fruit) }

    fun updateFruit(fruit: Fruit) =
        viewModelScope.launch(Dispatchers.IO) { repository.updateFruit(fruit) }

    fun deleteFruit(fruit: Fruit) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteFruit(fruit) }

    fun deleteFruitById(id: Int) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteFruitById(id) }

    fun clearFruit() =
        viewModelScope.launch(Dispatchers.IO) { repository.clearFruit() }
}