package com.rebrotesolution.smzr_android.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.models.Usuario

@Dao
interface PersonaDao {

    @Query("SELECT * FROM persona")
    fun getPersona(): LiveData<Persona>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePersona(persona:Persona):Long

    @Query("DELETE FROM persona")
    fun deletePersona()

}