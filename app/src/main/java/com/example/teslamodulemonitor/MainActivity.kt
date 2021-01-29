package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //need an activity that allows adding a pack
    fun goToAddPack(view: View?) {
//        Intent intent = new Intent(this, /*TODO: add class to go to*/);
    } //need an activity that shows pack module and cell values
}