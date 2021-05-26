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

  /*  suspend fun readByName(ff : String){
        spenddataDao.readByName(ff)
    }

    suspend fun readByAmount(Am : Int){
        spenddataDao.readByAmount(Am)
    }

    suspend fun readByReceivedOrSpend(x : String , y : String){
        spenddataDao.readByReceivedOrSpend(x,y)
    }
*/
}