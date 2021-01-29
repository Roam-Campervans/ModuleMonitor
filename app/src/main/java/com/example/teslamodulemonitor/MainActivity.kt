package com.example.tedslamodulemonitor

import TeslaModuleMonitor.Test
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.teslamodulemonitor.R
import com.example.teslamodulemonitor.addTestPack
import java.io.File
import java.io.FileInputStream


class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var allPacksList: ArrayList<Test.Pack>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        renderPack("SOME KEY FOR INTENT")
    }

    fun renderPack(strExtra: String) {
        // Steps to decode/display packs from protocol buffer
        // 1. Get input stream or uri to file
        val extras = intent.extras
        if (extras != null) {
            if (extras.containsKey(strExtra)) {

                // 2. Parse incoming steam into message object
                val uriString = intent.getStringExtra(strExtra).toString()
                val uri = Uri.parse(uriString)
                val inputStream = FileInputStream(File(uri.toString()))
                val pack = Test.Pack.parseFrom(inputStream)

                // 3. Set fields into View
                val textView = findViewById<TextView>(R.id.textViewDecode)
                // pass textView and Pack to render function
            }
        }
    }

    //need an activity that allows adding a pack
    fun goToAddPack(view: View?) {
        val intent = Intent(this, addTestPack::class.java)
        startActivity(intent)
    }
    //need an activity that shows pack module and cell values
}