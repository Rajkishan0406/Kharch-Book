package com.example.kharchbook.DataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//The Viewmodel's role is to provide data to UI and survive configuration chcnges.

class SpendDataViewModel(application: Application) : AndroidViewModel(application){

    val readAllSpendData : LiveData<List<SpendData>>
    private val repository : SpendDataRepository


    init{
        val spenddao = SpendDatabase.getSpendDataBase(application).spenddatadao()
        repository = SpendDataRepository(spenddao)
        readAllSpendData = repository.readAllSpendData
    }

     fun addSpendData(spenddata : SpendData){
        viewModelScope.launch {
            repository.addSpendData(spenddata)
        }
    }

    fun deletedata(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletedata(id)
        }
    }

   /* suspend fun readByName(ff : String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.readByName(ff)
        }
    }

    suspend fun readByName(Am : Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.readByAmount(Am)
        }
    }

    suspend fun readByReceivedOrSpend(x : String , y : String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.readByReceivedOrSpend(x, y)
        }
    }
*/

}