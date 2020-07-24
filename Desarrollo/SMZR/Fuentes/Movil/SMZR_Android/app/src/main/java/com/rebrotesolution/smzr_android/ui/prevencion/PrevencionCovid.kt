package com.rebrotesolution.smzr_android.ui.prevencion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.rebrotesolution.smzr_android.R

class PrevencionCovid : Fragment() {


    private lateinit var prevencionViewModel: PrevencionCovidViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prevencionViewModel = ViewModelProviders.of(this).get(PrevencionCovidViewModel::class.java)
        val root = inflater.inflate(R.layout.prevencion_covid_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        prevencionViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }


}
