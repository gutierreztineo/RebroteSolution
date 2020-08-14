package com.rebrotesolution.smzr_android.models

import android.text.TextUtils
import android.util.Patterns
import androidx.room.*

@Entity(
    tableName = "persona"
)
data class Persona(

    @PrimaryKey
    @ColumnInfo(name="id")
    var id_persona: Int?,

    @ColumnInfo(name="nombres")
    var nombres: String,

    @ColumnInfo(name="apellidos")
    var apellidos: String,

    @ColumnInfo(name="genero")
    var genero: String,

    @ColumnInfo(name="dni")
    var dni: String,

    @ColumnInfo(name="edad")
    var edad: Int,

    @ColumnInfo(name="email")
    var email: String,

    @Embedded
    var usuario: Usuario?
) {

    val isDatoCorreoValid: Boolean
        get() = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()

    val isDatoPersonal1Valid: Boolean
        get() = !TextUtils.isEmpty(nombres) && !TextUtils.isEmpty(apellidos) && !TextUtils.isEmpty(dni)

    val isDniValid: Boolean
        get() = dni.length == 8


}