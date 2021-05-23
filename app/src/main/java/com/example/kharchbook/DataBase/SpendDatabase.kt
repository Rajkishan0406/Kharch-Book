package com.example.kharchbook.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Contains the database holder and serves as the main access point for the underlying connection
// to your app's persisted , relational data.

@Database(entities = [SpendData :: class], version = 1, exportSchema = false)
public abstract class SpendDatabase : RoomDatabase() {

    abstract fun spenddatadao() : SpendDataDao

    companion object{
        @Volatile
        private var INSTANCE : SpendDatabase? = null

        fun getSpendDataBase(context : Context) : SpendDatabase{
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SpendDatabase::class.java,
                        "Spend_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }

    }

}