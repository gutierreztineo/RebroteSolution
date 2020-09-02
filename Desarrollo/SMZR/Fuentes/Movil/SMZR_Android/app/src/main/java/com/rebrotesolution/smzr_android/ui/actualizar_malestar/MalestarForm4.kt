package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.MalestarForm4FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm4ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm4ViewModelFactory
import es.dmoral.toasty.Toasty
import kotlin.properties.Delegates

class MalestarForm4 : Fragment(), FormularioMalestarResultCallBacks {

    var q1 by Delegates.notNull<Int>()
    var q2 by Delegates.notNull<Int>()
    var q3 by Delegates.notNull<Int>()
    var q4 by Delegates.notNull<Int>()
    var q5 by Delegates.notNull<Int>()
    var q6 by Delegates.notNull<Int>()

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

        q1 = arguments?.getInt("q1")!!
        q2 = arguments?.getInt("q2")!!
        q3 = arguments?.getInt("q3")!!
        q4 = arguments?.getInt("q4")!!
        q5 = arguments?.getInt("q5")!!
        q6 = arguments?.getInt("q6")!!

        return form4Binding.root
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
            "q5" to q5,
            "q6" to q6,
            "q7" to data["q7"],
            "q8" to data["q8"]
        )
        navController.navigate(R.id.go_malestarForm5,bundle);
    }

}
