package com.example.kharchbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kharchbook.DataBase.SpendData
import com.example.kharchbook.DataBase.SpendDataViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SpendFragment : Fragment() {

    private lateinit var mSpendDataViewModel: SpendDataViewModel
    lateinit var fb : FloatingActionButton
    lateinit var recyclerView: RecyclerView

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_spend, container, false)

        fb = view.findViewById(R.id.fab)

        fb.setOnClickListener(View.OnClickListener {
            setFragment(AddSpendDataFragment())
        })

        recyclerView = view.findViewById(R.id.spend_recyclerview)
        val adapter = SpendAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)



        mSpendDataViewModel = ViewModelProvider(this).get(SpendDataViewModel::class.java)


        mSpendDataViewModel.readAllSpendData.observe(viewLifecycleOwner, Observer { user->
            adapter.setData(user)
        })

        return view
    }



    private fun setFragment(forgotFragment: AddSpendDataFragment) {
        var ft: FragmentTransaction? = getFragmentManager()?.beginTransaction()
        if (ft != null) {
            ft.replace(R.id.main_frame, forgotFragment)
        }
        if (ft != null) {
            ft.addToBackStack(null).commit()
        }
    }
}