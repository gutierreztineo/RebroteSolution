package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.MalestarForm7FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm7ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm7ViewModelFactory

class MalestarForm7 : Fragment(), FormularioMalestarResultCallBacks {

    private lateinit var form7ViewModel: MalestarForm7ViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val form7Binding = DataBindingUtil.inflate<MalestarForm7FragmentBinding>(inflater,R.layout.malestar_form7_fragment,container,false)
        form7ViewModel = ViewModelProviders.of(this,MalestarForm7ViewModelFactory(this)).get(MalestarForm7ViewModel::class.java)

        form7Binding.form7ViewModel = form7ViewModel
        form7Binding.lifecycleOwner = this

        return form7Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun incomplete() {
    }

    override fun complete() {
        navController.navigate(R.id.go_mostrarRecomendaciones);
    }

}
