package com.rebrotesolution.smzr_android.ui.historial_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.adapters.SintomaHistorialAdapter
import com.rebrotesolution.smzr_android.models.*
import com.rebrotesolution.smzr_android.viewModels.historial_malestar.HistorialMalestarViewModel
import kotlinx.android.synthetic.main.historial_fragment.*
import java.util.*
import kotlin.collections.ArrayList

class HistorialMalestar : Fragment() {

    private lateinit var historialViewModel: HistorialMalestarViewModel
    private lateinit var listaPersonaMalestares: ArrayList<PersonaMalestar>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SintomaHistorialAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historialViewModel = ViewModelProviders.of(this).get(HistorialMalestarViewModel::class.java)
        val root = inflater.inflate(R.layout.historial_fragment,container,false)
        recyclerView = root.findViewById(R.id.recicler_view_historial)
        cargarLista()
        mostrarData()
        return root
    }

    fun cargarLista(){
        var persona = Persona(id_persona=1, nombres="Junior Angel", apellidos="Morales Brenis",
            genero="Masculino", dni ="73508001", edad=18, email ="angel_96vir@hotmail.com", usuario = null
        )
        var grado = Grado(id_grado = 1,descripcion = "RIESGO")
        var malestar= Malestar(id_malestar = 1,descripcion = "Gripe", grado = grado)
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
