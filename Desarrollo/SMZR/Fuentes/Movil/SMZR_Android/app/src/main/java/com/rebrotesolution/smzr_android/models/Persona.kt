package com.rebrotesolution.smzr_android.models

import android.text.TextUtils
import org.w3c.dom.Text

class Persona (
    private var id_persona: Int,
    private var nombres: String,
    private var apellidos: String,
    private var genero: String,
    private var dni: String,
    private var edad: Int,
    private var email: String
) {

    val isDatoPersonal1Valid: Boolean
    get() = ( !TextUtils.isEmpty(getNombres()) && !TextUtils.isEmpty(getApellidos()) && !TextUtils.isEmpty(getDni())) && getDni().length == 8

    val isMayorEdad: Boolean
    get() = getEdad() >= 18

    val isGeneroSelected: Boolean
    get() = !TextUtils.isEmpty(getGenero())

    fun getIdPersona(): Int{
        return id_persona
    }

    fun getNombres(): String {
        return nombres
    }

    fun setNombres(name:String) {
        this.nombres = name
    }

    fun getApellidos():String{
        return apellidos
    }

    fun setApellidos(lastname: String){
        this.apellidos = lastname
    }
    fun getDni():String {
        return dni;
    }

    fun setDni(dni: String){
        this.dni = dni
    }

    fun getEdad():Int {
        return edad
    }

    fun setEdad(edad:Int){
        this.edad = edad
    }

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String){
        this.email = email
    }

    fun getGenero(): String {
        return genero
    }

    fun setGenero(genero: String){
        this.genero = genero
    }
}