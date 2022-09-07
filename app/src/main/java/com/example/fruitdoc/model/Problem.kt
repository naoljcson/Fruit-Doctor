package com.example.fruitdoc.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "problem")
data class Problem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "fruit_id")
    val fruitId: Int?,

    val type: String,

    val name: String,

    @ColumnInfo(name = "amharic_name")
    val amharicName: String,

    val description: String?,

    @ColumnInfo(name = "amharic_description")
    val amharicDescription: String?,

    val recommendation: String?,

    @ColumnInfo(name = "amharic_recommendation")
    val amharicRecommendation: String?,
)
