package com.rebrotesolution.smzr_android.models

data class Malestar (
     var id_malestar: Int?,
     var descripcion: String,
     var grado: Grado?,
     var recomendaciones: List<Recomendacion>?
){

}