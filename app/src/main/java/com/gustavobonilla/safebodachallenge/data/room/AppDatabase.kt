package com.gustavobonilla.safebodachallenge.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.gustavobonilla.safebodachallenge.data.entity.CityDaoEntity
import com.gustavobonilla.safebodachallenge.data.room.AppDatabase.Companion.VERSION

/**
 * Defines the Sqlite DB used for persistence of app.
 *
 * Uses Room for DB access.
 */
@Database(entities = [CityDaoEntity::class], version = VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    /**
     * Get the [CityDaoEntity] Dao.
     */
    abstract fun getCities(): CitiesDao

    companion object {
        const val VERSION = 1
    }
}