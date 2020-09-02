package com.rebrotesolution.smzr_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.models.HistorialAdapter
import com.rebrotesolution.smzr_android.models.PersonaMalestar
import kotlinx.android.synthetic.main.lista_sintomas_historial.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SintomaHistorialAdapter (
    private var lspersona_malestar: List<HistorialAdapter>
): RecyclerView.Adapter<SintomaHistorialAdapter.ViewHolder>() {

    var items:List<HistorialAdapter>?=null
    lateinit var viewHolder:ViewHolder

    init {
        this.items = lspersona_malestar
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vista = itemView
        var imagen: ImageView
        var fecha_registro: TextView
        var malestar_nombre: TextView

        init {
            imagen = vista.imagen_sintoma_categoria
            fecha_registro = vista.text_fecha_registro_historial
            malestar_nombre = vista.text_malestar_name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.lista_sintomas_historial,parent,false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imagen.setImageResource(lspersona_malestar.get(position).imagen)
        holder.fecha_registro.text = formatearFecha(lspersona_malestar.get(position).fecha)
        holder.malestar_nombre.text = lspersona_malestar.get(position).descripcion

    }

    private fun formatearFecha(fecha: Date):String{
        var sdf = SimpleDateFormat("EEE, d MMMM yyyy")
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(fecha)
    }
}