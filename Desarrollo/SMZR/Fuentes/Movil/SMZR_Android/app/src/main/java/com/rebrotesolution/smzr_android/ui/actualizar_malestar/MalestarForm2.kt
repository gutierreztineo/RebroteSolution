package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.MalestarForm2FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm2ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm2ViewModelFactory
import es.dmoral.toasty.Toasty
import kotlin.properties.Delegates

class MalestarForm2 : Fragment(), FormularioMalestarResultCallBacks {

    var q1 by Delegates.notNull<Int>()
    var q2 by Delegates.notNull<Int>()

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

        q1 = arguments?.getInt("q1")!!
        q2 = arguments?.getInt("q2")!!

        return form2Binding.root
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
            "q1" to q1,
            "q2" to q2,
            "q3" to data["q3"],
            "q4" to data["q4"]
        )
        navController.navigate(R.id.go_malestarForm3, bundle);
    }

}
