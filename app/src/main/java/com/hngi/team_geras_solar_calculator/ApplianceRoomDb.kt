package com.hngi.team_geras_solar_calculator

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ApplianceDetails::class], version = 2)
abstract class ApplianceRoomDb: RoomDatabase() {


    abstract fun applianceDao() : ApplianceDao


    companion object{
        @Volatile
        private var INSTANCE : ApplianceRoomDb? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : ApplianceRoomDb {
            return INSTANCE?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplianceRoomDb::class.java,
                    "Appliance_database"

                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ApplianceDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance

            }
        }
        private class ApplianceDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.applianceDao())
                    }
                }
            }
        }
        suspend fun populateDatabase(applianceDao: ApplianceDao) {
            applianceDao.deleteAll()

//            var appliance = ApplianceDetails("Hello",1000)
//            applianceDao.saveAppliance(appliance)
//            appliance = ApplianceDetails("World!", 4000)
//            applianceDao.saveAppliance(appliance)
        }
    }

}