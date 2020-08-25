package com.rebrotesolution.smzr_android.ui.prevencion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.viewModels.prevencion.PrevencionCovidViewModel
import me.relex.circleindicator.CircleIndicator3

class PrevencionCovid : Fragment() {


    private lateinit var prevencionViewModel: PrevencionCovidViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prevencionViewModel = ViewModelProviders.of(this).get(PrevencionCovidViewModel::class.java)
        val root = inflater.inflate(R.layout.prevencion_covid_fragment, container, false)
        val viewPager = root.findViewById<ViewPager2>(R.id.viewPager2)

        val pagesInformation : ArrayList<PreventionInformation> = ArrayList()

        pagesInformation.add(PreventionInformation("Lávate las manos hasta el antebrazo con agua y jabón por un mínimo de 20 segundos.",R.drawable.manos_covid))
        pagesInformation.add(PreventionInformation("Mantenga al menos 1 metro (3 pies) de distancia entre usted y las demás personas, particularmente aquellas que muestren síntomas como los del resfrío o gripe.",R.drawable.distancia2_covid))
        pagesInformation.add(PreventionInformation("Cúbrete la boca y la nariz con la mascarilla. Asegúrate de que no queden espacios entre esta y tu rostro.", R.drawable.mask_covid))
        pagesInformation.add(PreventionInformation("Recuerda seguir estos consejos para evitar contagiarte.",R.drawable.final_covid))

        viewPager.adapter = PrevencionPagerAdapter(pagesInformation)

        var indicator : CircleIndicator3 = root.findViewById(R.id.indicator)
        indicator.setViewPager(viewPager)

        return root
    }


}
