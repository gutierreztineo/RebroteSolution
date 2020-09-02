package com.rebrotesolution.smzr_android.viewModels.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.firebase.geofire.GeoFire
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository

class MainViewModel(
    var repository: UsuarioRepository
): ViewModel() {

    private lateinit var myLocationRef: DatabaseReference
    private lateinit var geoFire: GeoFire

    fun logOut(sharedPreferences: SharedPreferences){
        var id :Int = sharedPreferences.getInt("ID",0)
        repository.deleteSesion()
        myLocationRef = FirebaseDatabase.getInstance().getReference("ZonaRiesgo").child("Usuarios")
        myLocationRef.child(id.toString()).removeValue()
        geoFire = GeoFire(myLocationRef)
        geoFire!!.removeLocation(id.toString())
    }
}