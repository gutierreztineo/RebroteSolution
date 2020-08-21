package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.adapters.MostrarRecomendacionAdapter
import com.rebrotesolution.smzr_android.models.MalestarDTO
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class MostrarRecomendacionesViewModel(
    var recyclerView: RecyclerView,
    var context: Context,
    var listarespuestas: List<Int>
) : ViewModel() {

    private lateinit var adapter: MostrarRecomendacionAdapter

    fun mostrarData(){
        var list: ArrayList<MalestarDTO> = readItems(R.raw.malestares)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MostrarRecomendacionAdapter(list)
        recyclerView.adapter = adapter
    }

    @Throws(JSONException::class)
    private fun readItems(resource: Int): ArrayList<MalestarDTO> {
        val list = ArrayList<MalestarDTO>()
        val inputStream: InputStream = context.resources.openRawResource(resource)
        val json: String = Scanner(inputStream).useDelimiter("\\A").next()
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
           if(listarespuestas.get(i) != 0){
               val `object` = array.getJSONObject(i)
               val id_malestar = `object`.getInt("id_malestar")
               val descripcion = `object`.getString("descripcion")
               val lista_recomendaciones = `object`.getJSONArray("recomendaciones")
               for(j in 0 until lista_recomendaciones.length()){
                   val `recomendacion` = lista_recomendaciones.getJSONObject(j)
                   val id_recomendacion = `recomendacion`.getInt("id_recomendacion")
                   val texto = `recomendacion`.getString("descripcion")
                   list.add(MalestarDTO(id_malestar = id_malestar,titulo = descripcion,recomendacion = texto,imagen = obtenerImagen(id_recomendacion) ))
               }
           }
        }
        return list
    }

    private fun obtenerImagen(id: Int): Int{
        return when(id){
            1 -> R.drawable.ic_paciente
            2 -> R.drawable.ic_paciente
            3 -> R.drawable.ic_temperatura_ambiente
            4 -> R.drawable.ic_tos
            5 -> R.drawable.ic_tos
            6 -> R.drawable.ic_cansado
            7 -> R.drawable.ic_respiracion_dificultosa
            8 -> R.drawable.ic_no_fumar
            9 -> R.drawable.ic_farmaco
            10 -> R.drawable.ic_no_alcohol
            11 -> R.drawable.ic_no_comida
            12 -> R.drawable.ic_dolor_de_garganta
            13 -> R.drawable.ic_comer
            14 -> R.drawable.ic_agua
            15 -> R.drawable.ic_cereal
            16 -> R.drawable.ic_jabon
            17 -> R.drawable.ic_no_tocar_ojo
            18 -> R.drawable.ic_mano
            19 -> R.drawable.ic_dolor_de_cabeza
            20 -> R.drawable.ic_ruido
            21 -> R.drawable.ic_dormir
            22 -> R.drawable.ic_sal
            else -> R.drawable.baseline_ballot_24
        }
    }

}
