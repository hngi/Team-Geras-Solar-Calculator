package com.hngi.team_geras_solar_calculator

import androidx.annotation.WorkerThread

class ApplianceRepository(private val applianceDao: ApplianceDao) {

    val appliances = applianceDao.getAppliances()

    @WorkerThread
    suspend fun saveAppliance(appliance : ApplianceDetails){
        applianceDao.saveAppliance(appliance)
    }

    @WorkerThread
    suspend fun updateQuantity(quantity : Int, name: String) {
        applianceDao.updateQuantity(quantity,name)
    }

    @WorkerThread
    suspend fun delete(applianceDetails: ApplianceDetails) {
        applianceDao.delete(applianceDetails)
    }
}