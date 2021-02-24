package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.teslamodulemonitor.R
import com.example.teslamodulemonitor.addTestPack
import java.io.File
import java.io.FileInputStream
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        var numOfPacks = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        renderPack("SOME KEY FOR INTENT")
    }

    private fun renderPack(strExtra: String) {
        // Steps to decode/display packs from protocol buffer
        // 1. Get input stream or uri to file
        val file = File(filesDir, "protoOut")
        if (file.exists()) {
            // 2. Parse incoming steam into message object
            val inputStream = FileInputStream(file)
            val scr = Scanner(inputStream)
            val pack = Test.Pack.parseFrom(inputStream)
            Log.i("packStreamTest", pack.packName)
            Log.i("packStreamTest", pack.toString())
            scr.hasNextByte()
            print("Byte array: " + pack.toByteArray())


            // 3. Set fields into View
            val textView = findViewById<TextView>(R.id.textViewDecode)
            // pass textView and Pack to render function
            displayPackValues(textView, pack)
        }
    }

    //need an activity that allows adding a pack
    fun goToAddPack(view: View?) {
        val intent = Intent(this, addTestPack::class.java)
        startActivityForResult(intent,1)
    }
        // Handle the returned Uri
    //need an activity that shows pack module and cell values

    fun displayPackValues(textView: TextView, pack: Test.Pack){
        var str = StringBuilder()
        str.append("Pack name: ${pack.packName}\nPack Voltage:${pack.currentVoltage}\nPack Temp:${pack.averagePacktemp}\n"
                + "Number of modules in ${pack.packName} is: ${pack.numberOfModules}\n")
        for (mod in pack.modulesList){
            str.append("Module ${mod.id} is ${mod.moduleVoltage}V and ${mod.moduleTemp}DegC\n" +
                    "Highest voltage cell is ${mod.highestCellVolt}V\n" +
                    "Lowest voltage cell is ${mod.lowestCellVolt}V \n")
            for (cell in mod.cellsList){
                str.append("Cell${cell.cellId} is ${cell.cellVolt}V\n")
            }
        }
        textView.text = str
    }
}