package com.example.fruitdoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitdoc.database.FruitDoctorDatabase
import com.example.fruitdoc.model.Notification
import com.example.fruitdoc.repository.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationViewModel( application: Application) : AndroidViewModel(application) {

    val repository: NotificationRepository

    // initialize dao, repository and all events
    init {
        val dao = FruitDoctorDatabase.getDatabase(application).notificationDao()
        repository = NotificationRepository(dao)
    }

    fun insertNotification(notification: Notification) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertNotification(notification) }

    suspend fun allNotifications(): List<Notification> {
        var notifications:  List<Notification>
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {  notifications = repository.allNotifications()  }
        return notifications
    }
}