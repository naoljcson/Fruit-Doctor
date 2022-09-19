package com.example.fruitdoc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fruitdoc.dao.ProblemDao
import com.example.fruitdoc.dao.ProductDao
import com.example.fruitdoc.dao.FruitDao
import com.example.fruitdoc.dao.NotificationDao
import com.example.fruitdoc.model.Problem
import com.example.fruitdoc.model.Product
import com.example.fruitdoc.model.Fruit
import com.example.fruitdoc.model.Notification
import com.example.fruitdoc.util.Constants.DB_NAME


@Database(entities = [Fruit::class, Problem::class, Product::class,Notification::class], version = 3)
abstract class FruitDoctorDatabase : RoomDatabase() {

    abstract fun fruitDao(): FruitDao
    abstract fun problemDao(): ProblemDao
    abstract fun productDao(): ProductDao
    abstract fun notificationDao(): NotificationDao

    companion object {
        // Volatile annotation means any change to this field
        // are immediately visible to other threads.
        @Volatile
        private var INSTANCE: FruitDoctorDatabase? = null

        fun getDatabase(context: Context): FruitDoctorDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FruitDoctorDatabase::class.java,
                    DB_NAME
                )
                    .createFromAsset("database/fruit_doctor.db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}