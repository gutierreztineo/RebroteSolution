package com.rebrotesolution.smzr_android.ui.mapa_riesgo

import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.rebrotesolution.smzr_android.R
import es.dmoral.toasty.Toasty
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList


class MapaRiesgo : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var mProvider: HeatmapTileProvider
    private lateinit var mMap: GoogleMap
    private var mapReady = false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.mapa_riesgo_fragment, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapa_principal) as SupportMapFragment
        mapFragment?.getMapAsync{
            googleMap -> mMap = googleMap
            mapReady = true
            setUpMap()
        }
        return root
    }


    private fun setUpMap(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()){ location ->
            lastLocation = location
            val currentLatLong = LatLng(location.latitude,location.longitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,13f))
        }

        addHeatMap()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {

        }
    }

    /*
    fun addHeatMapWeighted() {
        var gradient: Gradient = Gradient(colors, startpoints);
        var wDat: List<WeightedLatLng> = CrimeData().getWeightedPositions();
        val provider: HeatmapTileProvider = HeatmapTileProvider.Builder().weightedData(wDat)
            .gradient(gradient).build();
        mMap.addTileOverlay(TileOverlayOptions().tileProvider(provider));
    }

    var colors = intArrayOf(
        Color.GREEN,  // green(0-50)
        Color.YELLOW,  // yellow(51-100)
        Color.rgb(255, 165, 0),  //Orange(101-150)
        Color.RED,  //red(151-200)
        Color.rgb(153, 50, 204),  //dark orchid(201-300)
        Color.rgb(165, 42, 42) //brown(301-500)
    )

    var startpoints = floatArrayOf(
        0.1f, 0.2f, 0.3f, 0.4f, 0.6f, 1.0f
    )*/

    private fun addHeatMap() {
        var list: List<LatLng?>? = null

        // Get the data: latitude/longitude positions of police stations.
        try {
            list = readItems(R.raw.police_stations)
        } catch (e: JSONException) {
            Toasty.error(requireContext(), "Problem reading list of locations.", Toast.LENGTH_LONG).show()
        }
        val colors = intArrayOf(
            Color.rgb(102, 225, 0),  // green
            Color.rgb(255, 0, 0) // red
        )

        val startPoints = floatArrayOf(
            0.2f, 1f
        )

        val gradient = Gradient(colors, startPoints)

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        mProvider = HeatmapTileProvider.Builder()
            .data(list)
            .gradient(gradient)
            .build()
        // Add a tile overlay to the map, using the heat map tile provider.
        val title : TileOverlayOptions = TileOverlayOptions()
        title.tileProvider(mProvider)
        var mOverlay = mMap.addTileOverlay(title)
    }

    @Throws(JSONException::class)
    private fun readItems(resource: Int): ArrayList<LatLng?>? {
        val list = ArrayList<LatLng?>()
        val inputStream: InputStream = resources.openRawResource(resource)
        val json: String = Scanner(inputStream).useDelimiter("\\A").next()
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
            val `object` = array.getJSONObject(i)
            val lat = `object`.getDouble("lat")
            val lng = `object`.getDouble("lng")
            list.add(LatLng(lat, lng))
        }
        return list
    }


}
