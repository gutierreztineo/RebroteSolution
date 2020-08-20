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
import com.rebrotesolution.smzr_android.databinding.MalestarForm2FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm2ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm2ViewModelFactory

class MalestarForm2 : Fragment(), FormularioMalestarResultCallBacks {

    private lateinit var form2ViewModel: MalestarForm2ViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val form2Binding = DataBindingUtil.inflate<MalestarForm2FragmentBinding>(inflater,R.layout.malestar_form2_fragment, container, false)
        form2ViewModel = ViewModelProviders.of(this,MalestarForm2ViewModelFactory(this)).get(MalestarForm2ViewModel::class.java)

        form2Binding.form2ViewModel = form2ViewModel
        form2Binding.lifecycleOwner = this

        return form2Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun incomplete() {

    }

    override fun complete() {
        navController.navigate(R.id.go_malestarForm3);
    }

}
