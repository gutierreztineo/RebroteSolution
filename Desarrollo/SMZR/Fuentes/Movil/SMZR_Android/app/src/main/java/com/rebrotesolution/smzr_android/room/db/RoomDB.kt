package com.rebrotesolution.smzr_android.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.room.dao.UsuarioDao

@Database(
    entities = [Usuario::class],
    version = 1
)
abstract class RoomDB : RoomDatabase() {

    abstract fun getUserDao(): UsuarioDao

    companion object{

        @Volatile
        private var instance: RoomDB? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
            instance
                ?: buildDataBase(
                    context
                )
                    .also { instance = it }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "MyDataBase.db"
        ).build()
    }
}