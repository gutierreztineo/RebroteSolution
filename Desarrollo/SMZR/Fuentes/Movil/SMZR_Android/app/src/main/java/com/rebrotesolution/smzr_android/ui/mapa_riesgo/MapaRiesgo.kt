package com.rebrotesolution.smzr_android.ui.mapa_riesgo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.firebase.geofire.GeoFire
import com.firebase.geofire.GeoLocation
import com.firebase.geofire.GeoQuery
import com.firebase.geofire.GeoQueryEventListener
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.firebase.database.*
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.rebrotesolution.smzr_android.LoginActivity
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.ApiResultCallBacks
import com.rebrotesolution.smzr_android.interfaces.IOnLoadLocationListener
import com.rebrotesolution.smzr_android.models.MyLatLng
import com.rebrotesolution.smzr_android.models.models_api.MyLatLngDTO
import com.rebrotesolution.smzr_android.viewModels.factory.MapaRiesgoViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.mapa_riesgo.MapaRiesgoViewModel
import es.dmoral.toasty.Toasty


class MapaRiesgo : Fragment(), ApiResultCallBacks, IOnLoadLocationListener, GeoQueryEventListener {

    private lateinit var mapaViewModel: MapaRiesgoViewModel
    private var mapReady = false

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder

    private val channelId = "com.rebrotesolution.smzr_android.service_worker"
    private val description = "Notificacion de alerta de zona de riesgo"


    private  var mMap: GoogleMap? = null
    private lateinit var mProvider: HeatmapTileProvider
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var myLocationRef: DatabaseReference
    private lateinit var zonaRiesgo: MutableList<LatLng>
    private lateinit var listener: IOnLoadLocationListener

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var miCiudad: DatabaseReference
    private lateinit var lastLocation: Location
    private  var geoQuery: GeoQuery? = null
    private lateinit var geoFire: GeoFire

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreferences = requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        Dexter.withActivity(requireActivity())
            .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    buildLocationRequest()
                    buildLocationCallBack()
                    fusedLocationClient =
                        LocationServices.getFusedLocationProviderClient(requireActivity())
                    initArea()
                    settingGeoFire()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toasty.warning(
                        requireContext(),
                        "Debes habilitar el permiso de ubicación",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }).check()

        mapaViewModel =
            ViewModelProviders.of(this, MapaRiesgoViewModelFactory(this, requireContext()))
                .get(MapaRiesgoViewModel::class.java)
        val root = inflater.inflate(R.layout.mapa_riesgo_fragment, container, false)

        return root
    }


   private fun setUpMap() {
       if (mMap != null) {
           mMap!!.clear()
           addCircleArea()
       }
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        mMap!!.uiSettings.isZoomControlsEnabled = true
        fusedLocationClient!!.requestLocationUpdates(locationRequest,locationCallback!!, Looper.myLooper())

        mMap!!.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            lastLocation = location
            val currentLatLong = LatLng(location.latitude, location.longitude)
            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 13f))
        }
    }

    override fun onHttpOk(message: String) {
    }

    override fun onHttpError(message: String) {
        Toasty.error(requireContext(), "Problem reading list of locations.", Toast.LENGTH_LONG)
            .show()
    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest!!.interval = 5000
        locationRequest!!.fastestInterval = 3000
        locationRequest!!.smallestDisplacement = 10f
    }

    private fun buildLocationCallBack() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                if (mMap != null) {
                    lastLocation = p0!!.lastLocation
                    addLocationPosition()
                }
            }
        }
    }

    private fun initArea() {
        var id = sharedPreferences.getInt("ID",0)
        miCiudad = FirebaseDatabase.getInstance()
            .getReference("ZonaRiesgo")
            .child("Usuarios")
        miCiudad.child(id.toString()).removeValue()

        listener = this

        miCiudad!!.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val latLngList = ArrayList<MyLatLng>()
                for (locationSnapShot in p0.children) {
                    val latLngdto = locationSnapShot.getValue(MyLatLngDTO::class.java)
                    if(!locationSnapShot.key.equals(id.toString())){
                        latLngList.add(MyLatLng(latLngdto!!.l[0], latLngdto!!.l[1]))
                    }
                }
                listener!!.onLocationLoadSuccess(latLngList)
            }

        })
    }

    private fun settingGeoFire() {
        var id = sharedPreferences.getInt("ID",0)
        myLocationRef = FirebaseDatabase.getInstance().getReference("ZonaRiesgo").child("Usuarios")
        myLocationRef.child(id.toString()).removeValue()
        geoFire = GeoFire(myLocationRef)
    }


    private fun addCircleArea() {
        if (geoQuery != null) {
            geoQuery!!.removeGeoQueryEventListener(this@MapaRiesgo)
            geoQuery!!.removeAllListeners()
        }

        for (latLng in zonaRiesgo!!) {
            mMap!!.addCircle(
                CircleOptions().center(latLng)
                    .radius(500.0)
                    .strokeWidth(0.001f)
            )

            geoQuery = geoFire!!.queryAtLocation(
                GeoLocation(latLng.latitude, latLng.longitude),
                0.15
            ) //0.01 = 10 metros
            geoQuery!!.addGeoQueryEventListener(this@MapaRiesgo)
        }

        val colors = intArrayOf(
            Color.rgb(102, 225, 0),  // green
            Color.rgb(255, 0, 0) // red
        )

        val startPoints = floatArrayOf(
            0.2f, 1f
        )

        val gradient = Gradient(colors, startPoints)

        mProvider = HeatmapTileProvider.Builder()
            .data(zonaRiesgo)
            .gradient(gradient)
            .radius(50)
            .build()
        val title : TileOverlayOptions = TileOverlayOptions()
        title.tileProvider(mProvider)
        var mOverlay = mMap!!.addTileOverlay(title)
    }

    private fun addLocationPosition(){
        var id = sharedPreferences.getInt("ID",0)
        geoFire!!.setLocation(id.toString(),GeoLocation(lastLocation!!.latitude,lastLocation!!.longitude))
    }

    /*
    private fun addZonaRiesgoToFirebase(){
        zonaRiesgo = ArrayList()
        zonaRiesgo.add(LatLng(-12.1125074,-77.0755772))
        zonaRiesgo.add(LatLng(-12.2125074,-77.1755772))
        zonaRiesgo.add(LatLng(-12.3125074,-77.2755772))

        FirebaseDatabase.getInstance()
            .getReference("ZonaRiesgo")
            .child("Usuarios")
            .setValue(zonaRiesgo)
            .addOnCompleteListener { Toasty.success(requireContext(),"Update",Toast.LENGTH_SHORT).show() }
            .addOnFailureListener{
                Toasty.error(requireContext(),it.message!!,Toast.LENGTH_SHORT)
            }
    }*/

    override fun onLocationLoadSuccess(latLngs: List<MyLatLng>) {
        zonaRiesgo = ArrayList()
        for (myLatLng in latLngs) {
            val convert = LatLng(myLatLng.latitude, myLatLng.longitude)
            zonaRiesgo!!.add(convert)
        }

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapa_principal) as SupportMapFragment

        mapFragment?.getMapAsync { googleMap ->
            mMap = googleMap
            mapReady = true
            setUpMap()
        }
    }

    override fun onLocationLoadFailed(message: String) {
        Toasty.error(requireContext(), message, Toast.LENGTH_LONG)
            .show()
    }

    override fun onGeoQueryReady() {
    }

    override fun onKeyEntered(key: String?, location: GeoLocation?) {
        Toasty.warning(requireContext(), "Estas en la zona de riesgo, ¡CUIDADO!", Toast.LENGTH_LONG)
            .show()
        generatePushNotification("¡CUIDADO! Estás acercandote a una zona de riesgo, toma tus precauciones.",Color.RED)
    }

    override fun onKeyMoved(key: String?, location: GeoLocation?) {
    }

    override fun onKeyExited(key: String?) {
        Toasty.success(requireContext(), "Saliste de la zona de riesgo", Toast.LENGTH_LONG).show()
        generatePushNotification("¡TODO BIEN! estas fuera de la zona de riesgo.",Color.GREEN)
    }

    override fun onGeoQueryError(error: DatabaseError?) {
        Toasty.error(requireContext(), error!!.message, Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        fusedLocationClient!!.removeLocationUpdates(locationCallback!!)
        super.onStop()
    }

    private fun generatePushNotification(message: String, color: Int) {

        notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent =
            PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val contentView = RemoteViews(requireContext().packageName, R.layout.notification_layout)
        contentView.setInt(R.id.layout_notify_background,"setBackgroundColor",color)
        contentView.setTextViewText(R.id.notify_title, "SMZR")
        contentView.setTextViewText(
            R.id.notify_content,
            message
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(requireContext(), channelId)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.ic_launcher
                    )
                )
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
        } else {
            builder = Notification.Builder(context)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.ic_launcher
                    )
                )
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
        }

        notificationManager.notify(100, builder.build())
    }
}
