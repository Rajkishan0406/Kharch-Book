package com.example.kharchbook

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.kharchbook.DataBase.SpendData
import com.example.kharchbook.DataBase.SpendDataViewModel

class SpendDataDetailsFragment(ci: SpendData) : Fragment() {

    lateinit var From : TextView
    lateinit var Amount : TextView
    lateinit var Date : TextView
    lateinit var Message : TextView
    lateinit var Mode : TextView
    lateinit var bun : Bundle
    lateinit var status : TextView
    lateinit var card : CardView
    lateinit var del : CardView
    lateinit var mSpendDataViewModel: SpendDataViewModel

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

        mSpendDataViewModel = ViewModelProvider(this).get(SpendDataViewModel::class.java)



        From = view.findViewById(R.id.name)
        Amount = view.findViewById(R.id.money)
        Message = view.findViewById(R.id.message)
        Mode = view.findViewById(R.id.modee)
        Date = view.findViewById(R.id.date)
        status = view.findViewById(R.id.modeex)
        card = view.findViewById(R.id.stat)
        del = view.findViewById(R.id.delete)
        var ID = id?.toInt()

        del.setOnClickListener(View.OnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Alert")
            alert.setMessage("Are you sure, you want to delete this?")
            alert.setPositiveButton("Yes"){ _, _ ->
                if (ID != null) {
                    mSpendDataViewModel.deletedata(ID)
                    Toast.makeText(activity,"Data deleted",Toast.LENGTH_SHORT).show()
                    var pref = PreferenceManager.getDefaultSharedPreferences(activity)
                    pref.apply {
                        val editor = pref.edit()
                        var before = pref.getString("Balance", "0")
                        if (mode != null) {
                            if (mode.substring(2,3).equals("2")) {
                                val z = 0 as Int
                                val zo = 0 as Int
                                var money = (before?.toInt() ?: z) + (amount?.toInt() ?: zo)
                                var Money = money.toString()
                                editor.putString("Balance", Money)
                            } else {
                                val z = 0 as Int
                                val zo = 0 as Int
                                var money =  (before?.toInt() ?: z) - (amount?.toInt() ?: zo)
                                var Money = money.toString()
                                editor.putString("Balance", Money)
                            }
                        }
                        editor.apply()
                    }
                    setFragment(SpendFragment())
                }
            }
            alert.setNegativeButton("No"){_, _->

            }
            alert.create().show()
        })



        Date.setText(date)
        From.setText(from)
        Amount.setText(amount)
        Message.setText(msg)
        if (mode != null) {
            if(mode.substring(0,1).equals("2")) {
                Mode.setText("Offline")
            }
        }
        if (mode != null) {
            if(mode.substring(2,3).equals("2")) {
                card.setCardBackgroundColor(Color.RED)
                status.setText("Spend")
                Amount.setTextColor(Color.RED)
            }
        }



        return view
    }

    private fun setFragment(forgotFragment: SpendFragment) {
        var ft: FragmentTransaction? = getFragmentManager()?.beginTransaction()
        if (ft != null) {
            ft.replace(R.id.main_frame, forgotFragment)
        }
        if (ft != null) {
            ft.addToBackStack(null).commit()
        }
    }


}
