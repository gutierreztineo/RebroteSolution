package com.rebrotesolution.smzr_android.viewModels.mapa_riesgo

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.ApiResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.historial_malestar.HistorialMalestarViewModel
import es.dmoral.toasty.Toasty
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class MapaRiesgoViewModel(
    var httplistener: ApiResultCallBacks,
    var context: Context
) : ViewModel() {

    private lateinit var mProvider: HeatmapTileProvider

    fun addHeatMap(mMap: GoogleMap){
        var list: List<LatLng?>? = null

        // Get the data: latitude/longitude positions of police stations.
        try {
            list = readItems(R.raw.police_stations)
        } catch (e: JSONException) {
           httplistener.onHttpError(e.message!!)
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
            .radius(50)
            .build()
        // Add a tile overlay to the map, using the heat map tile provider.
        val title : TileOverlayOptions = TileOverlayOptions()
        title.tileProvider(mProvider)
        var mOverlay = mMap.addTileOverlay(title)
    }

    @Throws(JSONException::class)
    private fun readItems(resource: Int): ArrayList<LatLng?>? {
        val list = ArrayList<LatLng?>()
        val inputStream: InputStream = context.resources.openRawResource(resource)
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
