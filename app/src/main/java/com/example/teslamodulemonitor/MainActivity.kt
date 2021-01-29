package com.example.tedslamodulemonitor

import TeslaModuleMonitor.Test
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.teslamodulemonitor.R
import com.example.teslamodulemonitor.addTestPack



class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var allPacksList: ArrayList<Test.Pack>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    //need an activity that allows adding a pack
    fun goToAddPack(view: View?) {
        val intent = Intent(this, addTestPack::class.java)
        startActivity(intent)
    }
    //need an activity that shows pack module and cell values
}