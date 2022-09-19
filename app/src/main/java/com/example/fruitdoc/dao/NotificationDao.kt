package com.example.fruitdoc.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fruitdoc.model.Notification

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotification(notification: Notification)

    @Query("Select * from notification order by id ASC")
    fun allNotifications(): List<Notification>
}