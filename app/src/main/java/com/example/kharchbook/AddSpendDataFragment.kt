package com.example.kharchbook

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.kharchbook.DataBase.SpendData
import com.example.kharchbook.DataBase.SpendDataViewModel
import org.w3c.dom.Text

class AddSpendDataFragment : Fragment() {

    lateinit var add : CardView
    lateinit var amount : TextView
    lateinit var to : TextView
    lateinit var msg : TextView
    lateinit var date : TextView
    lateinit var online : CardView
    lateinit var offline : CardView
    lateinit var spend : CardView
    lateinit var received : CardView
    var on = 0 as Int
    private var On = 0 as Int
    lateinit var mSpendDataViewModel : SpendDataViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_add_spend_data, container, false)

        add = view.findViewById(R.id.add_spenddata)
        amount = view.findViewById(R.id.send_amount)
        to = view.findViewById(R.id.send_to)
        msg = view.findViewById(R.id.send_message)
        date = view.findViewById(R.id.send_date)
        online = view.findViewById(R.id.online_card)
        offline = view.findViewById(R.id.offline_card)
        spend = view.findViewById(R.id.spend_card)
        received = view.findViewById(R.id.received_card)

        spend.setOnClickListener(View.OnClickListener {
            On = 1
            spend.setCardBackgroundColor(Color.LTGRAY)
            received.setCardBackgroundColor(Color.WHITE)
        })

        received.setOnClickListener(View.OnClickListener {
            On = -1
            received.setCardBackgroundColor(Color.LTGRAY)
            spend.setCardBackgroundColor(Color.WHITE)
        })

        online.setOnClickListener(View.OnClickListener {
            on = 1;
            online.setCardBackgroundColor(Color.LTGRAY)
            offline.setCardBackgroundColor(Color.WHITE)
        })

        offline.setOnClickListener(View.OnClickListener {
            on = -1;
            offline.setCardBackgroundColor(Color.LTGRAY)
            online.setCardBackgroundColor(Color.WHITE)
        })

        mSpendDataViewModel = ViewModelProvider(this).get(SpendDataViewModel::class.java)

        add.setOnClickListener(View.OnClickListener {
            if(On == 0 || on == 0 || date.text.toString().length == 0 || msg.text.toString().length == 0 || to.text.toString().length == 0 || amount.text.toString().length == 0){
                Toast.makeText(activity,"Please fill all details",Toast.LENGTH_SHORT).show()
            }
            else{
                InsertData()
            }
        })

        return view
    }

    private fun InsertData() {
        val Amount = (amount.text.toString()).toInt() as Int
        val Date = date.text.toString()
        val From = to.text.toString()
        val M = msg.text.toString()
        var Through = ""
        var status = "";
        if(on == 1)
             Through = "1"
        else
            Through = "2"
        if(On == 1)
            Through = Through + " 2"
        else
            Through = Through + " 1"

        //Create SpendData Object
        val spend = SpendData(0,Amount,Date,From,Through,M)
        mSpendDataViewModel.addSpendData(spend)
        Toast.makeText(activity,"Data saved",Toast.LENGTH_SHORT).show()
        setFragment(SpendFragment())
    }

    private fun setFragment(forgotFragment: SpendFragment) {
        var ft: FragmentTransaction? = getFragmentManager()?.beginTransaction()
        if (ft != null) {
            ft.replace(R.id.main_frame, forgotFragment)
        }
        if (ft != null) {
            ft.commit()
        }
    }

}
