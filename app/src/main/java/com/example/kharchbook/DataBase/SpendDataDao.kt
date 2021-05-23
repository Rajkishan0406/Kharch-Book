package com.example.kharchbook.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Contains method for accessing the SpendData database

@Dao
interface SpendDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSpendData(spendsata : SpendData)

    @Query("SELECT * FROM MoneySpend ORDER BY id ASC")
    fun readAllSpendData() : LiveData<List<SpendData>>


}