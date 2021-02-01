package com.example.pruebaintermediakotlin.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ConsumptionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsumption(consumptions: Consumptions)

    @Query("SELECT * FROM Consumptions_table")
    fun getAllConsumption(): LiveData<List<Consumptions>>

    @Delete
    suspend fun deleteConsumption(consumptions: Consumptions)

    @Query("DELETE FROM consumptions_table")
    suspend fun deleteAllConsumption()
}