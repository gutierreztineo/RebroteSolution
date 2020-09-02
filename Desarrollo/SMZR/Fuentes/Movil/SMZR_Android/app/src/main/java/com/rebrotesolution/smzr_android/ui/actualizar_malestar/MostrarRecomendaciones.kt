package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.MostrarRecomendacionesFragmentBinding
import com.rebrotesolution.smzr_android.interfaces.ApiResultCallBacks
import com.rebrotesolution.smzr_android.interfaces.ButtonAcceptHandler
import com.rebrotesolution.smzr_android.interfaces.StartCallBacks
import com.rebrotesolution.smzr_android.models.MalestarDTO
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.api.LoginClient
import com.rebrotesolution.smzr_android.network.api.MalestarClient
import com.rebrotesolution.smzr_android.network.api.PersonaClient
import com.rebrotesolution.smzr_android.network.repository.MalestarRepository
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.room.db.RoomDB
import com.rebrotesolution.smzr_android.ui.extras.LoadingDialog
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MostrarRecomendacionesViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MostrarRecomendacionesViewModelFactory
import es.dmoral.toasty.Toasty
import kotlin.properties.Delegates

class MostrarRecomendaciones : Fragment(), StartCallBacks, ApiResultCallBacks {

    private lateinit var initList: List<Int>

    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private lateinit var mostrarRecomViewModel: MostrarRecomendacionesViewModel
    private lateinit var loading: LoadingDialog


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
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val apiMalestar = MalestarClient(networkConnectionInterceptor)
        var repository = MalestarRepository(apiMalestar)
        val sharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val mostrarRecomendacionBinding =
            DataBindingUtil.inflate<MostrarRecomendacionesFragmentBinding>(
                inflater,
                R.layout.mostrar_recomendaciones_fragment,
                container,
                false
            )

        recyclerView = mostrarRecomendacionBinding.reciclerViewRecomendaciones
        mostrarRecomViewModel = ViewModelProviders.of(
            this,
            MostrarRecomendacionesViewModelFactory(
                recyclerView,
                requireContext(),
                initList,
                this,
                this,
                repository,
                sharedPreferences
            )
        ).get(MostrarRecomendacionesViewModel::class.java)
        mostrarRecomViewModel.mostrarData()
        mostrarRecomendacionBinding.recomendacionesViewModel = mostrarRecomViewModel
        mostrarRecomendacionBinding.lifecycleOwner = this

        loading = LoadingDialog(requireActivity())

        return mostrarRecomendacionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun startLoading() {
        loading.start()
    }

    override fun onHttpOk(message: String) {
        loading.dismiss()
        navController.navigate(R.id.nav_historial_malestar);
    }

    override fun onHttpError(message: String) {
        loading.dismiss()
        Toasty.error(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
