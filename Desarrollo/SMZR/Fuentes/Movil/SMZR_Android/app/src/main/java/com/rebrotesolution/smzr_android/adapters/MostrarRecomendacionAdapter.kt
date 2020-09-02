package com.rebrotesolution.smzr_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.models.MalestarDTO
import kotlinx.android.synthetic.main.lista_mostrar_recomendaciones.view.*

class MostrarRecomendacionAdapter(
   private var lsmalestares: ArrayList<MalestarDTO>
): RecyclerView.Adapter<MostrarRecomendacionAdapter.ViewHolder>() {

    var items:ArrayList<MalestarDTO>?=null
    lateinit var viewHolder:ViewHolder

    init {
        this.items = lsmalestares
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vista = itemView
        var imagen: ImageView
        var titulo_malestar: TextView
        var recomendacion_descripcion: TextView

        init {
            imagen = vista.imagen_recomendacion
            titulo_malestar = vista.text_title_malestar
            recomendacion_descripcion = vista.text_descripcion_recomendacion
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.lista_mostrar_recomendaciones,parent,false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imagen.setImageResource(lsmalestares.get(position).imagen!!)
        holder.titulo_malestar.text = lsmalestares.get(position).titulo
        holder.recomendacion_descripcion.text = lsmalestares.get(position).recomendacion
    }
}