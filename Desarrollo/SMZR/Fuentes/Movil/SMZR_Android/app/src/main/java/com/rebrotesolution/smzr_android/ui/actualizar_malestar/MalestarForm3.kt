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
import com.rebrotesolution.smzr_android.databinding.MalestarForm3FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm3ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm3ViewModelFactory
import es.dmoral.toasty.Toasty
import kotlin.properties.Delegates

class MalestarForm3 : Fragment(), FormularioMalestarResultCallBacks {

    var q1 by Delegates.notNull<Int>()
    var q2 by Delegates.notNull<Int>()
    var q3 by Delegates.notNull<Int>()
    var q4 by Delegates.notNull<Int>()

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

        q1 = arguments?.getInt("q1")!!
        q2 = arguments?.getInt("q2")!!
        q3 = arguments?.getInt("q3")!!
        q4 = arguments?.getInt("q4")!!

        return form3Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun incomplete(message: String) {
        Toasty.warning(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    override fun complete(data: Map<String,Int>) {
        var bundle:Bundle = bundleOf(
            "q1" to q1,
            "q2" to q2,
            "q3" to q3,
            "q4" to q4,
            "q5" to data["q5"],
            "q6" to data["q6"]
        )
        navController.navigate(R.id.go_malestarForm4,bundle);
    }

}
