package com.example.kharchbook.DataBase

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

// A repository class abstracts access to multiple data sources.
// The repository is not a part of the Architecture Components libraries,
// but is a suggested best practice for cofr seperation and architecture

class SpendDataRepository(private val spenddataDao : SpendDataDao) {

    val readAllSpendData : LiveData<List<SpendData>> = spenddataDao.readAllSpendData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addSpendData(spend : SpendData){
        spenddataDao.addSpendData(spend)
    }

    suspend fun deletedata(id : Int){
        spenddataDao.deletedata(id)
    }

}