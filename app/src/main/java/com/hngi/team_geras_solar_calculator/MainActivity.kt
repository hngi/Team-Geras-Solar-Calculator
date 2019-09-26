package com.hngi.team_geras_solar_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button.setOnClickListener {
            val appliance = editText.text.toString()
            val wattage = editText2.text.toString().toInt()

            val applianceDetails = ApplianceDetails(appliance,wattage,1)
            ApplianceViewModel(application).insert(applianceDetails)

//            val intent = Intent(this, ApplianceActivity::class.java)
//            startActivity(intent)
            finish()
        }
    }
}
