package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.rebrotesolution.smzr_android.R

class ActualizarMalestar : Fragment() {

    private lateinit var actMalestarViewModel: ActualizarMalestarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actMalestarViewModel = ViewModelProviders.of(this).get(ActualizarMalestarViewModel::class.java)
        val root = inflater.inflate(R.layout.actualizar_malestar_fragment, container, false)
        val textView : TextView = root.findViewById(R.id.text_home)
        actMalestarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

}
