package com.rebrotesolution.smzr_android.models

import java.util.*

data class PersonaMalestar(
    private var id_persona_malestares: Int?,
    private var persona: Persona,
    private var malestar: Malestar,
    private var fecha_registro: Date
) {

    fun getIdPersonaMalestar():Int{
        return id_persona_malestares!!
    }

    fun setIdPersonaMalestar(id_persona_malestares: Int?){
        this.id_persona_malestares = id_persona_malestares
    }

    fun getPersona():Persona{
        return persona
    }

    fun setPersona(persona: Persona){
        this.persona = persona
    }

    fun getMalestar(): Malestar{
        return malestar
    }

    fun setMalestar(malestar: Malestar){
        this.malestar  = malestar
    }

    fun getFechaRegistro(): Date{
        return fecha_registro
    }

    fun setFechaRegistro(fecha_registro: Date){
        this.fecha_registro = fecha_registro
    }
}