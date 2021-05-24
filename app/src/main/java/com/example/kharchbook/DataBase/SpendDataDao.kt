package com.example.kharchbook.DataBase

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*

//Contains method for accessing the SpendData database

@Dao
interface SpendDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSpendData(spendsata : SpendData)

    @Query("SELECT * FROM MoneySpend ORDER BY id ASC")
    fun readAllSpendData() : LiveData<List<SpendData>>

    @Query("DELETE FROM MoneySpend WHERE id = :Id")
    suspend fun deletedata (Id : Int)


}