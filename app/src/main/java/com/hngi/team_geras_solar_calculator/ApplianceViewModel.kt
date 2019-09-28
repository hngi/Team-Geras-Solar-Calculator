package com.hngi.team_geras_solar_calculator

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplianceViewModel(application: Application): AndroidViewModel(application) {

    private val applianceRepository: ApplianceRepository

//    var total : Int
    val allAppliances: LiveData<List<ApplianceDetails>>

    init {
        val applianceDao = ApplianceRoomDb.getDatabase(application, viewModelScope).applianceDao()
        applianceRepository = ApplianceRepository(applianceDao)
        allAppliances = applianceRepository.appliances
//        total = ApplianceAdapter().total
    }

    fun insert(applianceDetails: ApplianceDetails) = viewModelScope.launch(Dispatchers.IO) {
        applianceRepository.saveAppliance(applianceDetails)
    }

    fun update(quantity : Int, name: String) = viewModelScope.launch(Dispatchers.IO) {
        applianceRepository.updateQuantity(quantity, name)
    }

    fun delete(applianceDetails: ApplianceDetails) = viewModelScope.launch(Dispatchers.IO){
        applianceRepository.delete(applianceDetails)
    }

    }
