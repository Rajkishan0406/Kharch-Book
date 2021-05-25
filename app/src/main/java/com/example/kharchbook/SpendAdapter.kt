package com.example.kharchbook

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.kharchbook.DataBase.SpendData
import com.example.kharchbook.DataBase.SpendDataViewModel
import kotlin.coroutines.coroutineContext

class SpendAdapter : RecyclerView.Adapter<SpendAdapter.MyViewHolder>() {

    private var spendlist = emptyList<SpendData>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var to = itemView.findViewById(R.id.To) as TextView
        var amount = itemView.findViewById(R.id.Amount) as TextView
        var date = itemView.findViewById(R.id.date) as TextView
        var c = itemView.findViewById(R.id.card) as CardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.spenddata_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = spendlist[position]

        holder.amount.text = currentItem.amount.toString()
        holder.to.text = currentItem.from
        holder.date.text = currentItem.date




        holder.c.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var activity = v!!.context as AppCompatActivity
                val IDF = SpendDataDetailsFragment(currentItem)
                var bun: Bundle
                bun = Bundle()
                bun.putString("id",currentItem.id.toString())
                bun.putString("from", currentItem.from)
                bun.putString("amount", currentItem.amount.toString())
                bun.putString("message", currentItem.message)
                bun.putString("mode", currentItem.payment_gateway)
                bun.putString("date", currentItem.date)
                IDF.arguments = bun
                activity.supportFragmentManager.beginTransaction().replace(R.id.main_frame, IDF)
                    .addToBackStack(null).commit()
            }
        })




    }

    override fun getItemCount(): Int {
        return spendlist.size
    }

    fun setData(user : List<SpendData>){
        this.spendlist = user
        notifyDataSetChanged()
    }

}