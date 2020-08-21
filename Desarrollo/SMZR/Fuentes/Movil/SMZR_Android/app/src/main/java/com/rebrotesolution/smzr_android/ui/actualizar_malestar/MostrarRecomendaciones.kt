package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.models.MalestarDTO
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MostrarRecomendacionesViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MostrarRecomendacionesViewModelFactory
import kotlin.properties.Delegates

class MostrarRecomendaciones : Fragment() {

    private lateinit var initList: List<Int>

    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private lateinit var mostrarRecomViewModel: MostrarRecomendacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initList = listOf(
            arguments?.getInt("q1")!!,
            arguments?.getInt("q2")!!,
            arguments?.getInt("q3")!!,
            arguments?.getInt("q4")!!,
            arguments?.getInt("q5")!!,
            arguments?.getInt("q6")!!,
            arguments?.getInt("q7")!!,
            arguments?.getInt("q8")!!,
            arguments?.getInt("q9")!!,
            arguments?.getInt("q10")!!,
            arguments?.getInt("q11")!!,
            arguments?.getInt("q12")!!,
            arguments?.getInt("q13")!!,
            arguments?.getInt("q14")!!
        )
        val root = inflater.inflate(R.layout.mostrar_recomendaciones_fragment, container, false)
        recyclerView = root.findViewById(R.id.recicler_view_recomendaciones)
        mostrarRecomViewModel = ViewModelProviders.of(
            this,
            MostrarRecomendacionesViewModelFactory(recyclerView, requireContext(), initList)
        ).get(MostrarRecomendacionesViewModel::class.java)
        mostrarRecomViewModel.mostrarData()
        val button = requireActivity().findViewById<Button>(R.id.btn_cerrar_recomendaciones)
        button.setOnClickListener(clickListener)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    val clickListener = View.OnClickListener {view ->
        navController.navigate(R.id.nav_historial_malestar);
    }


}
