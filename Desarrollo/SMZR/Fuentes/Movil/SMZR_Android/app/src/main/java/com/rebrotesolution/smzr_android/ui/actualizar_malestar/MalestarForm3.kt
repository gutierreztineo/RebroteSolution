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
import com.rebrotesolution.smzr_android.databinding.MalestarForm3FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm3ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm3ViewModelFactory

class MalestarForm3 : Fragment(), FormularioMalestarResultCallBacks {

    private lateinit var form3ViewModel: MalestarForm3ViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val form3Binding = DataBindingUtil.inflate<MalestarForm3FragmentBinding>(inflater,R.layout.malestar_form3_fragment, container, false)
        form3ViewModel = ViewModelProviders.of(this,MalestarForm3ViewModelFactory(this)).get(MalestarForm3ViewModel::class.java)

        form3Binding.form3ViewModel = form3ViewModel
        form3Binding.lifecycleOwner = this

        return form3Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun incomplete() {

    }

    override fun complete() {
        navController.navigate(R.id.go_malestarForm4);
    }

}
