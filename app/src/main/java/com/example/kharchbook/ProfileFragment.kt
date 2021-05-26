package com.example.kharchbook

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ProfileFragment : Fragment() {

    lateinit var money : TextView
    lateinit var los : ImageView
    lateinit var gan : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        money = view.findViewById(R.id.My_money)
        los = view.findViewById(R.id.loss)
        gan = view.findViewById(R.id.gain)


        var pref = PreferenceManager.getDefaultSharedPreferences(activity)
        var mm = pref.getString("Balance","0")

        if (mm != null) {
            if(mm.toInt() < 0){
                gan.visibility = View.INVISIBLE
                money.setText(mm)
                money.setTextColor(Color.RED)
            }
            else{
                los.visibility = View.INVISIBLE
                money.setText(mm)
                money.setTextColor(Color.GREEN)
            }
        }

        return view
    }
}