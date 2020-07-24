package com.rebrotesolution.smzr_android.ui.cuenta

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.rebrotesolution.smzr_android.R

class MiCuenta : Fragment() {

    private lateinit var miCuentaViewModel: MiCuentaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        miCuentaViewModel = ViewModelProviders.of(this).get(MiCuentaViewModel::class.java)
        val root = inflater.inflate(R.layout.mi_cuenta_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        miCuentaViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

}
