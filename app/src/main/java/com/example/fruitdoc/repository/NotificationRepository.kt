package com.example.fruitdoc.repository

import com.example.fruitdoc.dao.NotificationDao
import com.example.fruitdoc.model.Notification

class NotificationRepository(private val notificationDao: NotificationDao){
    // get all the notifications
    fun allNotifications(): List<Notification> = notificationDao.allNotifications()

    // adds an notification to our database.
    suspend fun insertNotification(notification: Notification) {
        notificationDao.insertNotification(notification)
    }
}