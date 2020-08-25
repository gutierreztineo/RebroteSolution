package com.rebrotesolution.smzr_android.viewModels.register

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.ApiTimeOutException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException
import kotlinx.android.synthetic.main.datos_personales3_fragment.view.*

class DatosPersonales3ViewModel(
    private val listener: RegisterResultCallBacks,
    private val repository: PersonaRepository,
    private val persona: Persona,
    private val context: Context,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private var gen: String = "Masculino"

    fun onRegisterCompleteClicked(v: View) {
        listener.onStarted()
        persona.genero = gen
        Coroutines.main {
            try {
                val tokenResponse = repository.registrarUsuario(persona.usuario!!.username,persona!!.usuario!!.password)
                tokenResponse.token?.let {
                    val save = sharedPreferences.edit()
                    save.putString("TOKEN",it)
                    save.apply()
                    val profileResponse= repository.registrarPersona(persona,it)
                    profileResponse.data?.let { data ->
                        var persona = Persona(data.id,data.firstname,data.lastnamep,data.lastnamem,data.gender,data.dni,data.birthdate,data.email,null)
                        repository.savePersonaInLocal(persona)
                        listener?.onSuccess(it)
                        return@main
                    }
                    return@main
                }
            } catch (e: ApiException) {
                listener?.onError(e.message!!)
            } catch (e: NoInternetException) {
                listener?.onError(e.message!!)
            }catch( e: ApiTimeOutException){
                listener.onError(e.message!!)
            }
        }
    }

    fun onCustomCheckChanged(radio: RadioGroup?, id: Int) {
        when (id) {
            R.id.rb_masculino -> {
                val rb_m: RadioButton = radio!!.get(0) as RadioButton
                rb_m.background =
                    ContextCompat.getDrawable(context, R.drawable.rb_background_select)
                val rb_f: RadioButton = radio!!.get(1) as RadioButton
                rb_f.background =
                    ContextCompat.getDrawable(context, R.drawable.rb_background_deselect)
            }
            R.id.rb_femenino -> {
                val rb_f: RadioButton = radio!!.get(1) as RadioButton
                rb_f.background =
                    ContextCompat.getDrawable(context, R.drawable.rb_background_select)
                val rb_m: RadioButton = radio!!.get(0) as RadioButton
                rb_m.background =
                    ContextCompat.getDrawable(context, R.drawable.rb_background_deselect)
            }
            else -> null
        }
        gen = when (id) {
            R.id.rb_masculino -> "Masculino"
            R.id.rb_femenino -> "Femenino"
            else -> ""
        }
    }
}
