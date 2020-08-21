package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.MalestarForm1FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm1ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm1ViewModelFactory
import es.dmoral.toasty.Toasty

class MalestarForm1 : Fragment(), FormularioMalestarResultCallBacks {

    private lateinit var form1ViewModel: MalestarForm1ViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val form1Binding = DataBindingUtil.inflate<MalestarForm1FragmentBinding>(inflater,R.layout.malestar_form1_fragment,container,false)
        form1ViewModel = ViewModelProviders.of(this, MalestarForm1ViewModelFactory(this)).get(MalestarForm1ViewModel::class.java)

        form1Binding.form1ViewModel = form1ViewModel
        form1Binding.lifecycleOwner = this

        return form1Binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun incomplete(message: String) {
        Toasty.warning(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    override fun complete(data: Map<String,Int>) {
        var bundle: Bundle = bundleOf(
            "q1" to data["q1"],
            "q2" to data["q2"]
        )
        navController.navigate(R.id.go_malestarForm2,bundle);
    }

}
