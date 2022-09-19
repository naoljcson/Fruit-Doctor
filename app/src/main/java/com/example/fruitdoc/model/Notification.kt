package com.example.fruitdoc.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "notification")
data class Notification(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    val title: String,

    val body: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?
)
