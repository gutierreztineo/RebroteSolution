package com.rebrotesolution.smzr_android

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.navigation.NavigationView
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.api.LoginClient
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.room.db.RoomDB
import com.rebrotesolution.smzr_android.service_worker.NotifyRiesgoWorker
import com.rebrotesolution.smzr_android.viewModels.factory.MainViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.main.MainViewModel
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private var doubleBackToExitPressedOnce: Boolean
    private lateinit var repository: UsuarioRepository
    private lateinit var viewModel: MainViewModel

    init {
        doubleBackToExitPressedOnce = false
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = LoginClient(networkConnectionInterceptor)
        val db = RoomDB(this)
        repository = UsuarioRepository(api, db)

        viewModel =  ViewModelProviders.of(this, MainViewModelFactory(repository)).get( MainViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_mi_cuenta, R.id.nav_historial_malestar,
        R.id.nav_prevencion_covid, R.id.nav_mapa_riesgo, R.id.nav_actualizar_malestar), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.menu.getItem(0).isEnabled = false
        navView.setNavigationItemSelectedListener(this)
        navView.itemIconTintList = null
        setPeriodicRequest()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        this.habilitarItem()
        when(item.itemId) {
            R.id.nav_cerrar_sesion -> {
                viewModel.logOut()
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                finish()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_actualizar_malestar -> {
                item.isEnabled = false
                navController.navigate(R.id.nav_actualizar_malestar);
            }
            R.id.nav_mapa_riesgo -> {
                item.isEnabled = false
                navController.navigate(R.id.nav_mapa_riesgo);
            }
            R.id.nav_historial_malestar -> {
                item.isEnabled = false
                navController.navigate(R.id.nav_historial_malestar);
            }
            R.id.nav_mi_cuenta -> {
                item.isEnabled = false
                navController.navigate(R.id.nav_mi_cuenta);
            }
            R.id.nav_prevencion_covid -> {
                item.isEnabled = false
                navController.navigate(R.id.nav_prevencion_covid);
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            if(doubleBackToExitPressedOnce){
                finish()
                return
            }else{
                doubleBackToExitPressedOnce = true
                Toast.makeText(this,"Presione nuevamente para salir de la aplicacion",Toast.LENGTH_LONG).show()
                Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 4000)
            }
        }
    }

    private fun habilitarItem(){
        navView.menu.forEach { item -> item.isEnabled = true }
    }

    fun setPeriodicRequest(){
         val periodicWorkRequest = PeriodicWorkRequest.Builder(NotifyRiesgoWorker::class.java,5,
             TimeUnit.MINUTES)
             .build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }


}
