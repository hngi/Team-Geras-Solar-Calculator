package com.hngi.team_geras_solar_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_appliance.*

class ApplianceActivity : AppCompatActivity() {

    lateinit var viewModel: ApplianceViewModel

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


            Log.d("XXX", "$appliance")
        })

        button2.setOnClickListener {
            adapter.sumLoad()

            total.text = "Total: " + adapter.total.toString() + " Watt(s)"
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddApplianceActivity::class.java)
            startActivity(intent)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        onBackPressed()
//        return super.onOptionsItemSelected(item)
//    }
}
