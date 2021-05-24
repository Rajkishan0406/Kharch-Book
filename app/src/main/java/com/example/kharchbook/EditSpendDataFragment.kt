package com.example.kharchbook

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

class EditSpendDataFragment : Fragment() {

    lateinit var add : CardView
    lateinit var amount : TextView
    lateinit var to : TextView
    lateinit var msg : TextView
    lateinit var date : TextView
    lateinit var online : CardView
    lateinit var offline : CardView
    var on = 0 as Int
    lateinit var mSpendDataViewModel : SpendDataViewModel
    lateinit var bun : Bundle


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_edit_spend_data, container, false)


        add = view.findViewById(R.id.update_spenddata)
        amount = view.findViewById(R.id.update_send_amount)
        to = view.findViewById(R.id.update_send_to)
        msg = view.findViewById(R.id.update_send_message)
        date = view.findViewById(R.id.update_send_date)
        online = view.findViewById(R.id.update_online_card)
        offline = view.findViewById(R.id.update_offline_card)

        bun = this.requireArguments()
        var from : String? = bun.getString("from")
        var Amount : String? = bun.getString("amount")
        var Date : String? = bun.getString("date")
        var Msg : String? = bun.getString("message")
        var mode : String? = bun.getString("mode")
        var id : String? = bun.getString("id")

        amount.setText(Amount)
        to.setText(from)
        msg.setText(Msg)
        date.setText(Date)

        online.setOnClickListener(View.OnClickListener {
            on = 1;
        })

        offline.setOnClickListener(View.OnClickListener {
            on = -1;
        })

        mSpendDataViewModel = ViewModelProvider(this).get(SpendDataViewModel::class.java)

        add.setOnClickListener(View.OnClickListener {
            if(on == 0 || date.text.toString().length == 0 || msg.text.toString().length == 0 || to.text.toString().length == 0 || amount.text.toString().length == 0){
                Toast.makeText(activity,"Please fill all details", Toast.LENGTH_SHORT).show()
            }
            else{
                UpdateData()
            }
        })


        return view
    }

    private fun UpdateData() {
        val Amount = (amount.text.toString()).toInt() as Int
        val Date = date.text.toString()
        val From = to.text.toString()
        val M = msg.text.toString()
        var Through = ""
        if(on == 1)
            Through = "Online"
        else
            Through = "Offline"

        //Create SpendData Object
        val spend = SpendData(0,Amount,Date,From,Through,M)
        //mSpendDataViewModel.addSpendData(spend)
        Toast.makeText(activity,"Data updated",Toast.LENGTH_SHORT).show()
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