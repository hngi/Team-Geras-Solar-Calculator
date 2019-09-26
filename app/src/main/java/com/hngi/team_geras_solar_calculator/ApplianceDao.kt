package com.hngi.team_geras_solar_calculator

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ApplianceDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAppliance(applianceDetails: ApplianceDetails)

    @Query("DELETE FROM appliance_table")
    suspend fun deleteAll()

    @Query("SELECT * from appliance_table ORDER BY applianceRating ASC")
    fun getAppliances(): LiveData<List<ApplianceDetails>>

    @Query("UPDATE appliance_table SET quantity= :quantity WHERE applianceName = :applianceName ")
    suspend fun updateQuantity(quantity: Int, applianceName: String )

    @Delete
    suspend fun delete(applianceDetails: ApplianceDetails)
}