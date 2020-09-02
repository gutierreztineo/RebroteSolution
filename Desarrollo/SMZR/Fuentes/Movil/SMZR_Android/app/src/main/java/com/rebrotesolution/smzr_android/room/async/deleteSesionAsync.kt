package com.rebrotesolution.smzr_android.room.async

import android.os.AsyncTask
import com.rebrotesolution.smzr_android.room.dao.PersonaDao
import com.rebrotesolution.smzr_android.room.dao.UsuarioDao

class deleteSesionAsync(
    private var mAsyncTaskDaoPersona : PersonaDao
): AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg voids: Void?): Void? {
        mAsyncTaskDaoPersona?.deletePersona()
        return null
    }
}