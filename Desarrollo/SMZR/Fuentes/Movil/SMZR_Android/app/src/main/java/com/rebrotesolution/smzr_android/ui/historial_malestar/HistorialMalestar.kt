package com.rebrotesolution.smzr_android.ui.historial_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.rebrotesolution.smzr_android.R

class HistorialMalestar : Fragment() {

    private lateinit var historialViewModel: HistorialMalestarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historialViewModel = ViewModelProviders.of(this).get(HistorialMalestarViewModel::class.java)
        val root = inflater.inflate(R.layout.historial_fragment,container,false)
        val textView: TextView = root.findViewById(R.id.text_home)
        historialViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

}
