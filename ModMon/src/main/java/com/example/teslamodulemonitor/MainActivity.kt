package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream

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
        renderPack()
    }


    private fun renderPack() {
        // Steps to decode/display packs from protocol buffer
        // 1. Get input stream or uri to file

        val file = File(filesDir, "protoOut")
        if (file.exists()) {
            // 2. Parse incoming steam into message object
            val inputStream = FileInputStream(file)
            Log.i("llama", "$inputStream     lllllllllAAAAAAAMMMMMMMAAAAAAA")
            val pack = Test.Pack.parseFrom(inputStream)
            Log.i("packStreamTest", pack.packName)
            Log.i("packStreamTest", pack.toString())



            // 3. Set fields into View
            // pass textView and Pack to render function

            displayPackValues(pack)
        }
    }



    //need an activity that allows adding a pack
    fun goToAddPack(view: View?) {
        val intent = Intent(this, addTestPack::class.java)
        startActivityForResult(intent, 1)
    }
        // Handle the returned Uri
    //need an activity that shows pack module and cell values

    fun displayPackValues(pack: Test.Pack){
//        val newFrag = ValueHolderFrag.newInstance("name","value").
//        val vhf= findViewById<ConstraintLayout>(R.id.lon)
//        vhf.findViewById<TextView>(R.id.nameOfHeldValue).setText("Pack Voltage")
//        vhf.findViewById<TextView>(R.id.value).setText("${pack.currentVoltage}")

        val packFrag = PackFrag.newInstance("name", "value")

        var str = StringBuilder()
        str.append("Pack name: ${pack.packName}\nPack Voltage:${pack.currentVoltage}\nPack Temp:${pack.averagePacktemp}\n"
                + "Number of modules in ${pack.packName} is: ${pack.numberOfModules}\n")
        for (mod in pack.modulesList){
//            val
            str.append("Module ${mod.id} is ${mod.moduleVoltage}V and ${mod.moduleTemp}DegC\n" +
                    "Highest voltage cell is ${mod.highestCellVolt}V\n" +
                    "Lowest voltage cell is ${mod.lowestCellVolt}V \n")
            for (cell in mod.cellsList){
                str.append("Cell${cell.cellId} is ${cell.cellVolt}V\n")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val file = File(filesDir, "protoOut")
        if (file.exists()) {
            file.delete()
        }
    }
}