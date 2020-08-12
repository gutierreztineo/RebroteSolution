package com.rebrotesolution.smzr_android.models

data class Malestar (
    private var id_malestar: Int?,
    private var descripcion: String,
    private var grado: Grado
){
    fun getIdMalestar():Int{
        return id_malestar!!
    }

    fun setIdMalestar(id_malestar: Int?){
        this.id_malestar = id_malestar
    }

    fun getDescripcion():String {
        return descripcion
    }

    fun setDescripcion(descripcion: String){
        this.descripcion = descripcion
    }

    fun getGrado():Grado {
        return grado
    }

    fun setGrado(grado: Grado){
        this.grado = grado
    }
}