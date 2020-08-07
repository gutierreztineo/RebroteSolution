package com.rebrotesolution.smzr_android.ui.mapa_riesgo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.viewModels.mapa_riesgo.MapaRiesgoViewModel

class MapaRiesgo : Fragment(), OnMapReadyCallback {


    private lateinit var mapaRiesgoViewModel: MapaRiesgoViewModel
    private lateinit var mMap: GoogleMap

    companion object {
        var mapFragment : SupportMapFragment?=null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapaRiesgoViewModel = ViewModelProviders.of(this).get(MapaRiesgoViewModel::class.java)
        val root = inflater.inflate(R.layout.mapa_riesgo_fragment, container, false)

        mapFragment = fragmentManager?.findFragmentById(R.id.mapa_principal) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


}
