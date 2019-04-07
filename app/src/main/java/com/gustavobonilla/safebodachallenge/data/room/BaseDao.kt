package com.gustavobonilla.safebodachallenge.data.room

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import io.reactivex.Single

/**
 * Defines a [BaseDao] for all kind of model elements in App for DB persistence.
 */
interface BaseDao<T> {
    /**
     * Gets all the items of desired model by pagination (by page).
     *
     * @param page the page to get the results.
     */
    fun getAll(page: Int = 1): Single<List<T>>

    /**
     * Inserts a single element.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    /**
     * Inserts a list of elements.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insert(items: List<T>)

    companion object {
        const val PAGING = 100
    }
}