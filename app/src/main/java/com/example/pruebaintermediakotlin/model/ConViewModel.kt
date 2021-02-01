package com.example.pruebaintermediakotlin.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ConViewModel(application: Application):AndroidViewModel(application) {
    private val repository: ConRepository
    val allConsumptions: LiveData<List<Consumptions>>
    init {
        val conDao = ConDataBase.getDataBase(application).getConDao()
        repository = ConRepository(conDao)
        allConsumptions = repository.listAllConsumptions
    }
    fun insertConsumption(consumptions: Consumptions)= viewModelScope.launch {
        repository.insertConsumption(consumptions)
    }
    fun deleAllConsumption() = viewModelScope.launch {
        repository.deleteAllConsumption()
    }
    private var selectedConsumptions: MutableLiveData<Consumptions> = MutableLiveData()
    fun selected(consumptions: Consumptions?){
        selectedConsumptions.value = consumptions
    }
    fun selectedItem(): LiveData<Consumptions> = selectedConsumptions

    fun multiplicar(num1: Int,num2: Int)=num1*num2
}