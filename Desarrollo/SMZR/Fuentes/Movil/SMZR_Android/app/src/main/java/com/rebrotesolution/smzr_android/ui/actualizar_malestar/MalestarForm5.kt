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
import com.rebrotesolution.smzr_android.databinding.MalestarForm5FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm5ViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MalestarForm5ViewModelFactory
import es.dmoral.toasty.Toasty
import kotlin.properties.Delegates

class MalestarForm5 : Fragment(), FormularioMalestarResultCallBacks {

    var q1 by Delegates.notNull<Int>()
    var q2 by Delegates.notNull<Int>()
    var q3 by Delegates.notNull<Int>()
    var q4 by Delegates.notNull<Int>()
    var q5 by Delegates.notNull<Int>()
    var q6 by Delegates.notNull<Int>()
    var q7 by Delegates.notNull<Int>()
    var q8 by Delegates.notNull<Int>()

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

        q1 = arguments?.getInt("q1")!!
        q2 = arguments?.getInt("q2")!!
        q3 = arguments?.getInt("q3")!!
        q4 = arguments?.getInt("q4")!!
        q5 = arguments?.getInt("q5")!!
        q6 = arguments?.getInt("q6")!!
        q7 = arguments?.getInt("q7")!!
        q8 = arguments?.getInt("q8")!!

        return form5Binding.root
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
            "q7" to q7,
            "q8" to q8,
            "q9" to data["q9"],
            "q10" to data["q10"]
        )
        navController.navigate(R.id.go_malestarForm6,bundle);
    }


}
