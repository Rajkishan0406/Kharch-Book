package com.example.kharchbook.DataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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

}