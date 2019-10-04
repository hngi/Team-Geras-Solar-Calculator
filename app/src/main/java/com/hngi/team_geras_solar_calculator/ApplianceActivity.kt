package com.hngi.team_geras_solar_calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_appliance.*
import kotlinx.android.synthetic.main.result_page.*

class ApplianceActivity : AppCompatActivity() {

   private lateinit var viewModel: ApplianceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appliance)
        supportActionBar?.title = "All Appliances"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val applianceRv = findViewById<RecyclerView>(R.id.applianceRv)
        val adapter = ApplianceAdapter()
        applianceRv.adapter = adapter
        applianceRv.layoutManager = LinearLayoutManager(this)
        Log.d("XXX", "$adapter")
        viewModel = ViewModelProviders.of(this).get(ApplianceViewModel::class.java)

        viewModel.allAppliances
            .observe(this, Observer { appliance ->

                adapter.addAppliance(appliance)
             //   val number = appliance.size


                Log.d("XXX", "$appliance")
            })



        button2.setOnClickListener {




            adapter.sumLoad()

            if (adapter.total == 0) {
                Toast.makeText(this, "Please Add an Appliance", Toast.LENGTH_LONG).show()
            }
//            total.text =
//                "Total Load : " + adapter.total.toString() + " Watt(s)\nYou will need a solar power system that supplies above this load to run your home or office"
            else {
                floatingActionButton.visibility = View.GONE
                resultLayout.visibility = View.VISIBLE
                supportActionBar?.hide()
                button2.visibility = View.GONE
                resultTextView.text =
                    "Total Load : " + adapter.total.toString() + " Watt(s)\n\nYou will need a solar power system that supplies above this load to run your home or office"
            }

        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddApplianceActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {

        if (resultLayout.visibility == View.VISIBLE){
            resultLayout.visibility = View.GONE
            button2.visibility = View.VISIBLE
            supportActionBar?.show()
            floatingActionButton.visibility = View.VISIBLE
        }
        else
        super.onBackPressed()
    }
}
