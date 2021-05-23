package com.example.kharchbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import org.w3c.dom.Text

class SpendDataDetailsFragment : Fragment() {

    lateinit var From : TextView
    lateinit var Amount : TextView
    lateinit var Date : TextView
    lateinit var Message : TextView
    lateinit var Mode : TextView
    lateinit var bun : Bundle

override fun onStart() {
    super.onStart()
    (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_spend_data_details, container, false)

        bun = this.requireArguments()
        var from : String? = bun.getString("from")
        var amount : String? = bun.getString("amount")
        var date : String? = bun.getString("date")
        var msg : String? = bun.getString("message")
        var mode : String? = bun.getString("mode")
        var id : String? = bun.getString("id")


        From = view.findViewById(R.id.name)
        Amount = view.findViewById(R.id.money)
        Message = view.findViewById(R.id.message)
        Mode = view.findViewById(R.id.modee)
        Date = view.findViewById(R.id.date)


        Date.setText(date)
        From.setText(from)
        Amount.setText(amount)
        Message.setText(msg)
        if(mode.equals("offline"))
            Mode.setText("Offline")



        return view
    }
}
