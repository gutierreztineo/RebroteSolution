package com.rebrotesolution.smzr_android.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.room.dao.PersonaDao
import com.rebrotesolution.smzr_android.room.dao.UsuarioDao

@Database(
    entities = [Usuario::class, Persona::class],
    version = 2
)
abstract class RoomDB : RoomDatabase() {

    abstract fun getUserDao(): UsuarioDao
    abstract fun getPersonaDao(): PersonaDao

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
        ).fallbackToDestructiveMigration().build()
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `Persona` (`id` INTEGER, `name` TEXT, " +
                    "PRIMARY KEY(`id`))")
        }
    }
}