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
import com.rebrotesolution.smzr_android.databinding.MalestarForm5FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm5ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm5ViewModelFactory

class MalestarForm5 : Fragment(), FormularioMalestarResultCallBacks {

    private lateinit var form5ViewModel: MalestarForm5ViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val form5Binding = DataBindingUtil.inflate<MalestarForm5FragmentBinding>(inflater,R.layout.malestar_form5_fragment,container,false)
        form5ViewModel = ViewModelProviders.of(this,MalestarForm5ViewModelFactory(this)).get(MalestarForm5ViewModel::class.java)

        form5Binding.form5ViewModel = form5ViewModel
        form5Binding.lifecycleOwner = this

        return form5Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun incomplete() {
    }

    override fun complete() {
        navController.navigate(R.id.go_malestarForm6);
    }


}
