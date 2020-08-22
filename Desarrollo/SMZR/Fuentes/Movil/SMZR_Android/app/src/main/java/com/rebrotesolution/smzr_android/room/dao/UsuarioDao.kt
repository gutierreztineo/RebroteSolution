package com.rebrotesolution.smzr_android.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rebrotesolution.smzr_android.models.Usuario

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuario")
    fun getUser(): LiveData<Usuario>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSesion(usuario: Usuario):Long

    @Query("DELETE FROM usuario")
    fun deleteSesion()
}