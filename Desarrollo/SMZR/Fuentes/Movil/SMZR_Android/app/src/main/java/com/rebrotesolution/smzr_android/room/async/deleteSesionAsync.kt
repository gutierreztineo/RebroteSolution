package com.rebrotesolution.smzr_android.room.async

import android.os.AsyncTask
import com.rebrotesolution.smzr_android.room.dao.UsuarioDao

class deleteSesionAsync(
    private var mAsyncTaskDao: UsuarioDao
): AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg voids: Void?): Void? {
        mAsyncTaskDao?.deleteSesion()
        return null
    }
}