package com.rebrotesolution.smzr_android.viewModels.historial_malestar

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.adapters.SintomaHistorialAdapter
import com.rebrotesolution.smzr_android.models.*
import com.rebrotesolution.smzr_android.network.repository.MalestarRepository
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.ApiTimeOutException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException
import es.dmoral.toasty.Toasty
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistorialMalestarViewModel(
    var recyclerView: RecyclerView,
    var context: Context,
    var repo: MalestarRepository,
    var sharedPreferences: SharedPreferences

) : ViewModel() {

    lateinit var lista: ArrayList<HistorialAdapter>
    lateinit var listaPersonaMalestares: MutableLiveData<List<HistorialAdapter>>
    private lateinit var adapter: SintomaHistorialAdapter

    fun cargarLista(): MutableLiveData<List<HistorialAdapter>> {

        listaPersonaMalestares = MutableLiveData()
        Coroutines.main {
            try {
                var lista: ArrayList<HistorialAdapter> = ArrayList()
                var token = sharedPreferences.getString("TOKEN", "")
                val historialResponse = repo.obtenerHistorialMalestar(token!!)
                historialResponse.data?.let {
                    it.forEach { profile_ailment ->
                        lista.add(
                            HistorialAdapter(
                                profile_ailment.descripcion!!,
                                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(profile_ailment.inserted_at!!),
                                obtenerImagen(profile_ailment.ailment_levels_id)
                            )
                        )
                    }
                    listaPersonaMalestares.value = lista
                    return@main
                }
            } catch (e: ApiException) {
                Toasty.warning(context, e.message!!, Toast.LENGTH_SHORT)
            } catch (e: NoInternetException) {
                Toasty.error(context, e.message!!, Toast.LENGTH_SHORT)
            } catch (e: ApiTimeOutException) {
                Toasty.error(context, e.message!!, Toast.LENGTH_SHORT)
            }
        }
        return listaPersonaMalestares
    }

    fun mostrarData() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SintomaHistorialAdapter(listaPersonaMalestares.value!!)
        recyclerView.adapter = adapter
    }

    private fun obtenerImagen(id_nivel_malestar: Int): Int {
        return when (id_nivel_malestar) {
            1 -> R.drawable.ic_paciente
            2,3,4 -> R.drawable.ic_tos
            5,6,7 -> R.drawable.ic_cansado_nivel
            8,9,10 -> R.drawable.ic_respiracion_dificultosa
            11,12,13 -> R.drawable.ic_dolor_de_pecho
            14 -> R.drawable.ic_no_hablar
            15 -> R.drawable.ic_insonmio
            16 -> R.drawable.ic_cianosis
            17 -> R.drawable.ic_dolor_muscular
            18 -> R.drawable.ic_dolor_general
            19 -> R.drawable.ic_dolor_de_garganta
            20 -> R.drawable.ic_diarrea
            21 -> R.drawable.ic_ojos_rojos
            22,23 -> R.drawable.ic_dolor_de_cabeza
            24 -> R.drawable.ic_dolor_cabeza_severo
            25 -> R.drawable.ic_vomito
            else -> R.drawable.baseline_coronavirus_24

        }
    }
}
