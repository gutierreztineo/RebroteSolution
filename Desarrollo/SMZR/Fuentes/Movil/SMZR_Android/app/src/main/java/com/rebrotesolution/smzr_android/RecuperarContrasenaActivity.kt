package com.rebrotesolution.smzr_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.recuperar_contrasena1_fragment.*
import kotlinx.android.synthetic.main.recuperar_contrasena2_fragment.*

class RecuperarContrasenaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contrasena)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title="SMZR"

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

    }
    override fun onBackPressed() {
       // super.onBackPressed()
        finish()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}