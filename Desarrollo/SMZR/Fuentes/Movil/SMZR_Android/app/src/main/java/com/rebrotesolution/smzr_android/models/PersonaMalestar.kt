package com.rebrotesolution.smzr_android.models

import java.util.*

data class PersonaMalestar(
    var id_persona_malestares: Int?,
    var persona: Persona,
    var malestar: Malestar,
   var fecha_registro: Date
) {
}