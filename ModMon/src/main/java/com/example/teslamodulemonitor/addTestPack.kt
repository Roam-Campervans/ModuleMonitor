package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.teslamodulemonitor.MainActivity.Companion.numOfPacks
import java.io.File


private const val TAG = "AddTestPack"

class addTestPack : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_test_pack)
//        get intent?

//        generate random pack values
        var numberOfTestMods: Int = (1..5).random()
        var cellVolt: Float = randomTwoPointDecimal(320, 410)
        var modTemp: Float = randomTwoPointDecimal(1500, 2000)


//        build the pack
        var newPack: Test.Pack.Builder = Test.Pack.newBuilder()
                .setId(numOfPacks++)
                .setPackName("Test Pack${numOfPacks}")
                .setNumberOfModules(numberOfTestMods)
                .setAveragePacktemp(modTemp)
                .setCurrentVoltage((cellVolt.times(6)))


        modMaker(numberOfTestMods, cellVolt, modTemp, newPack)


//        Encode and write the pack to files dir then close activity

        write(newPack.build())
        finish()
}


//*************************** Helper Functions ***********************************
//

    /** Encode and write to local file for decoding */
    private fun write(pack: Test.Pack){

        var pbFile = File(filesDir,"protoOut")
//    file output is "/data/user/0/com.example.teslamodulemonitor/files"
        pack.writeTo(pbFile?.outputStream())
        Logger.i(TAG, "write: ${filesDir.absoluteFile}")
    }


    /** Generates Modules */
    private fun modMaker(numberOfTestMods: Int, cellvolt: Float, modTemp: Float, packBuilder: Test.Pack.Builder) {
        var modBuilder: Test.Pack.Module.Builder = Test.Pack.Module.newBuilder()
        for(i in 0 until numberOfTestMods) {
            modBuilder.setId(i.toString())
                    .setModuleTemp(modTemp)
                    .setHighestCellVolt(cellvolt)
                    .setLowestCellVolt(cellvolt)
                    .setModuleVoltage(cellvolt.times(6))
            cellMaker(cellvolt, modBuilder)
            packBuilder.addModules(modBuilder)
            modBuilder.clear()
        }
    }


    /** Generates cells for each module */
    private fun cellMaker(cellvolt: Float, modbuilder: Test.Pack.Module.Builder){
        for(i in 0 until 6) {
            modbuilder.addCells(Test.Pack.Module.Cell.newBuilder()
                    .setCellId(i + 1)
                    .setCellVolt(cellvolt)
            )
        }
    }

    /** Generates a random 2point decimal by taking your low and high times 100 ie "3.2 -> 320 & 4.2 -> 420 */
    fun randomTwoPointDecimal(yourMinTimes100: Int, yourMaxTimes100: Int):Float{
        val rnds = (yourMinTimes100..yourMaxTimes100).random()
        return rnds.times(0.01).toFloat()
    }

}