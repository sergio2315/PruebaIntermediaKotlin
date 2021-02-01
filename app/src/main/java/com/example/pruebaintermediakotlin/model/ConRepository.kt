package com.example.pruebaintermediakotlin.model

import androidx.lifecycle.LiveData

class ConRepository(private val consumptionsDao: ConsumptionsDao) {
    val listAllConsumptions: LiveData<List<Consumptions>> = consumptionsDao.getAllConsumption()

    suspend fun insertConsumption(consumptions: Consumptions){
        consumptionsDao.insertConsumption(consumptions)
    }
    suspend fun deleteConsumption(consumptions: Consumptions){
        consumptionsDao.deleteConsumption(consumptions)
    }
    suspend fun deleteAllConsumption(){
        consumptionsDao.deleteAllConsumption()
    }
}