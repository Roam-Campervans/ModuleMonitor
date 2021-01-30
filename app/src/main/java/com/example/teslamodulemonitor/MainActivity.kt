package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        var numOfPacks = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    //need an activity that allows adding a pack
    fun goToAddPack(view: View?) {
        val intent = Intent(this, addTestPack::class.java)
        startActivityForResult(intent,1)
    }
        // Handle the returned Uri
    //need an activity that shows pack module and cell values
}