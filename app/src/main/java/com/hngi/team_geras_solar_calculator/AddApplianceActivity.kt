package com.hngi.team_geras_solar_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_appliance.*

class AddApplianceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appliance)
        supportActionBar?.title = "New Appliance"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        button.setOnClickListener {

            editText.onEditorAction(EditorInfo.IME_ACTION_DONE)
            editText2.onEditorAction(EditorInfo.IME_ACTION_DONE)

            if (editText.text.isNullOrEmpty() || editText2.text.isNullOrEmpty()){
                Toast.makeText(this, "Text Fields must not be empty", Toast.LENGTH_LONG).show()
            }
            else{
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
