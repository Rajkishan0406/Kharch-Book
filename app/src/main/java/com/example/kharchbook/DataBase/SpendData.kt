package com.example.kharchbook.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "MoneySpend")
class SpendData (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    val amount : Int,
    val date : String,
    val from : String,
    val payment_gateway : String,
    val message : String,
   // val status : String
)
