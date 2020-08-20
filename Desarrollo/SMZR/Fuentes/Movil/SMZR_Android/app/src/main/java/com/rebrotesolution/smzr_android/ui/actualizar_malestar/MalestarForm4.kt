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
import com.rebrotesolution.smzr_android.databinding.MalestarForm4FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm4ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm4ViewModelFactory

class MalestarForm4 : Fragment(), FormularioMalestarResultCallBacks {

    private lateinit var form4ViewModel: MalestarForm4ViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val form4Binding = DataBindingUtil.inflate<MalestarForm4FragmentBinding>(inflater,R.layout.malestar_form4_fragment,container,false)
        form4ViewModel = ViewModelProviders.of(this,MalestarForm4ViewModelFactory(this)).get(MalestarForm4ViewModel::class.java)

        form4Binding.form4ViewModel = form4ViewModel
        form4Binding.lifecycleOwner = this

        return form4Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun incomplete() {
    }

    override fun complete() {
        navController.navigate(R.id.go_malestarForm5);
    }

}
