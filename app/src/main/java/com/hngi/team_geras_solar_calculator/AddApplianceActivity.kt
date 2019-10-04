package com.hngi.team_geras_solar_calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_appliance.*

class AddApplianceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appliance)
        supportActionBar?.title = "New Appliance"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val appliance = arrayOf(
            "deep freezer",
            "iron",
            "phone charger",
            "laptop charger",
            "Refrigerator",
            "Deep Freezer",
            "micro-wave",
            "Television",
            "Radio",
            "60watts Bulb",
            "100watts bulb"
        )
        val appAdapter = ArrayAdapter<String>(this, R.layout.appliance_drop_down, appliance)

//        val editText : AutoCompleteTextView = findViewById(R.id.editText)

        editText.setAdapter(appAdapter)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                editText2.keyListener != null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {


                when (p0.toString().toLowerCase()) {
                    "deep freezer" -> {
                        editText2.setText("450")
                        // editText2.keyListener = null
                    }
                    "iron" -> {
                        editText2.setText("1000")
                        //editText2.keyListener = null
                    }
                    "air conditioner" -> {
                        editText2.setText("2000")
                        // editText2.keyListener = null
                    }
                    "laptop charger" -> {
                        editText2.setText("75")
                        // editText2.keyListener != null
                    }
                    "phone charger" -> {
                        editText2.setText("20")
                        // editText2.keyListener != null
                    }
                    "60watts bulb" -> {
                        editText2.setText("60")
                    }
                    "100watts bulb" -> {
                        editText2.setText("100")
                    }
                    "micro-wave" -> {
                        editText2.setText("1500")
                    }
                    "television" -> {
                        editText2.setText("100")
                    }
                    "radio" -> {
                        editText2.setText("5")
                    }
                    "refrigerator" -> {
                        editText2.setText("200")
                    }

                    else -> {
                        editText2.setText("0")
                        // editText2.inputType = TYPE_CLASS_NUMBER
                    }
                }
            }


        })

        button.setOnClickListener {

            editText.onEditorAction(EditorInfo.IME_ACTION_DONE)
            editText2.onEditorAction(EditorInfo.IME_ACTION_DONE)

            if (editText.text.isNullOrEmpty() || editText2.text.isNullOrEmpty()) {
                Toast.makeText(this, "Text Fields must not be empty", Toast.LENGTH_LONG).show()
            } else {
                val appliance = editText.text.toString().toLowerCase()
                val wattage = editText2.text.toString().toInt()


                val applianceDetails = ApplianceDetails(appliance, wattage, 1)
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
