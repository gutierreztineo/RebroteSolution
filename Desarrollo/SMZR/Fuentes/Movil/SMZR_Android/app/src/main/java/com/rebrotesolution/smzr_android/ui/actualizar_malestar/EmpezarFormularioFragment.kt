package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.EmpezarFormularioFragmentBinding
import com.rebrotesolution.smzr_android.interfaces.ButtonAcceptHandler
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.EmpezarFormularioViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.EmpezarFormularioViewModelFactory

class EmpezarFormularioFragment : Fragment(), ButtonAcceptHandler {

    private lateinit var empezarFormularioViewModel: EmpezarFormularioViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val empezarFormularioBinding = DataBindingUtil.inflate<EmpezarFormularioFragmentBinding>(inflater,R.layout.empezar_formulario_fragment,container,false)
        empezarFormularioViewModel = ViewModelProviders.of(this,EmpezarFormularioViewModelFactory(this)).get(EmpezarFormularioViewModel::class.java)
        val textView : TextView = empezarFormularioBinding.root.findViewById(R.id.text_home)
        val buttonNext: Button = empezarFormularioBinding.root.findViewById(R.id.button_malestar)

        empezarFormularioViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        empezarFormularioViewModel.text_button.observe(viewLifecycleOwner, Observer {
            buttonNext.text = it
        })

        empezarFormularioBinding.empezarFormularioViewModel = empezarFormularioViewModel
        empezarFormularioBinding.lifecycleOwner = this

        return empezarFormularioBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun clickOnButton() {
        if (navController.currentDestination?.id == R.id.nav_empezar_formulario) {
            navController.navigate(R.id.go_malestarForm1)
        }
    }

}
