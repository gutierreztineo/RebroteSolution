package com.rebrotesolution.smzr_android.ui.prevencion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.R
import kotlinx.android.synthetic.main.info_prevencion_fragment.view.*

class PrevencionPagerAdapter(private val preventInfo : List<PreventionInformation>) : RecyclerView.Adapter<PrevencionPagerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevencionPagerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.info_prevencion_fragment,parent,false))
    }

    override fun getItemCount(): Int {
        return preventInfo.size
    }

    override fun onBindViewHolder(holder: PrevencionPagerAdapter.ViewHolder, position: Int) {
        val currentPageInfo = preventInfo[position]

        holder.description.text = currentPageInfo.description
        holder.image.setImageResource(currentPageInfo.image)
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        lateinit var image : ImageView
        lateinit var description : TextView

        constructor(itemView : View) : super(itemView){
            image = itemView.findViewById(R.id.info_image)
            description = itemView.findViewById(R.id.description_text)
        }
    }
}