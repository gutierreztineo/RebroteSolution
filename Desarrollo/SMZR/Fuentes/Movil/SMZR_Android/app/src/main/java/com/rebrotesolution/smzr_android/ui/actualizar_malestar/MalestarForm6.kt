package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.MalestarForm6FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm6ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm6ViewModelFactory

class MalestarForm6 : Fragment(), FormularioMalestarResultCallBacks {

    private lateinit var form6ViewModel: MalestarForm6ViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val form6Binding = DataBindingUtil.inflate<MalestarForm6FragmentBinding>(inflater,R.layout.malestar_form6_fragment,container,false)
        form6ViewModel = ViewModelProviders.of(this,MalestarForm6ViewModelFactory(this)).get(MalestarForm6ViewModel::class.java)

        form6Binding.form6ViewModel = form6ViewModel
        form6Binding.lifecycleOwner = this

        return form6Binding .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun incomplete() {
    }

    override fun complete() {
        navController.navigate(R.id.go_malestarForm7);
    }

}
