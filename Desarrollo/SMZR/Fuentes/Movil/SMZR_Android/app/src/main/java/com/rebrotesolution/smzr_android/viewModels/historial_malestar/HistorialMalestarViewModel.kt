package com.rebrotesolution.smzr_android.viewModels.historial_malestar

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.adapters.SintomaHistorialAdapter
import com.rebrotesolution.smzr_android.models.*
import java.util.*
import kotlin.collections.ArrayList

class HistorialMalestarViewModel(
    var recyclerView: RecyclerView,
    var context: Context

) : ViewModel() {

    lateinit var listaPersonaMalestares: ArrayList<PersonaMalestar>
    private lateinit var adapter: SintomaHistorialAdapter

    fun cargarLista(){
        var persona = Persona(id_persona=1, nombres="Junior Angel", apellidos="Morales Brenis",
            genero="Masculino", dni ="73508001", edad=18, email ="angel_96vir@hotmail.com", usuario = null
        )
        var grado = Grado(id_grado = 1,descripcion = "RIESGO")
        var malestar= Malestar(id_malestar = 1,descripcion = "Gripe", grado = grado,recomendaciones = ArrayList<Recomendacion>())
        var personamalestar = PersonaMalestar(id_persona_malestares = 1,persona = persona,malestar = malestar,fecha_registro = Date())
        listaPersonaMalestares = ArrayList()
        listaPersonaMalestares.add(personamalestar)
        listaPersonaMalestares.add(personamalestar)
        listaPersonaMalestares.add(personamalestar)
        listaPersonaMalestares.add(personamalestar)
        listaPersonaMalestares.add(personamalestar)
        listaPersonaMalestares.add(personamalestar)
        listaPersonaMalestares.add(personamalestar)
        listaPersonaMalestares.add(personamalestar)
    }

    fun mostrarData(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SintomaHistorialAdapter(listaPersonaMalestares)
        recyclerView.adapter = adapter
    }
}
