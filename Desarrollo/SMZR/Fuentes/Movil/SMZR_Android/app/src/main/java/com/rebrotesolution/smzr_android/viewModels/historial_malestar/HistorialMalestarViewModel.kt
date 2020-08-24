package com.rebrotesolution.smzr_android.viewModels.historial_malestar

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rebrotesolution.smzr_android.adapters.SintomaHistorialAdapter
import com.rebrotesolution.smzr_android.models.*
import com.rebrotesolution.smzr_android.network.repository.MalestarRepository
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException
import es.dmoral.toasty.Toasty
import java.util.*
import kotlin.collections.ArrayList

class HistorialMalestarViewModel(
    var recyclerView: RecyclerView,
    var context: Context,
    var repo: MalestarRepository

) : ViewModel() {

    lateinit var listaPersonaMalestares: ArrayList<PersonaMalestar>
    private lateinit var adapter: SintomaHistorialAdapter

    fun cargarLista(): ArrayList<PersonaMalestar>{
        /*var persona = Persona(id_persona=1, nombres="Junior Angel", apellidos="Morales Brenis",
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
        listaPersonaMalestares.add(personamalestar)*/
        listaPersonaMalestares = ArrayList()
        Coroutines.main {
            try {
                val apiResponse = repo.getHistorialMalestares(1)
                apiResponse.objeto?.let {
                    var gson = Gson()
                    var json = gson.toJson(it)
                    gson.fromJson(json,Persona::class.java)
                    /*listener.onSuccess(it.usuario!!)
                    userRepo.saveSesion(it.usuario!!)
                    personaRepo.savePersonaInLocal(it)*/
                    return@main
                }
                Toasty.error(context,apiResponse.message!!.toString(),Toast.LENGTH_SHORT)
            }catch (e: ApiException){
                Toasty.warning(context,e.message!!,Toast.LENGTH_SHORT)
            }catch (e: NoInternetException){
                Toasty.error(context,e.message!!,Toast.LENGTH_SHORT)
            }
        }
        return listaPersonaMalestares
    }

    fun mostrarData(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SintomaHistorialAdapter(listaPersonaMalestares)
        recyclerView.adapter = adapter
    }
}
