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
    suspend fun addSpendData(spendsata: SpendData)

    @Query("SELECT * FROM MoneySpend ORDER BY id ASC")
    fun readAllSpendData() : LiveData<List<SpendData>>

    @Query("DELETE FROM MoneySpend WHERE id = :Id")
    suspend fun deletedata(Id: Int)

   // @Query("SELECT * FROM MoneySpend WHERE `from` = :ff ORDER BY id ASC")
   // suspend fun readByName(ff : String)

    // @Query("SELECT * FROM MoneySpend WHERE amount > :Am ORDER BY id ASC")
    // suspend fun readByName(Am : Int)

    // @Query("SELECT * FROM MoneySpend WHERE (payment_gateway = :x || payment_gateway = :y)ORDER BY id ASC")
    // suspend fun readByReceivedOrSpend(x : String , y : String)

}