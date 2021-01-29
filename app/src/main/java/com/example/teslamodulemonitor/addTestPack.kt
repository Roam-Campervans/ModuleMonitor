package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tedslamodulemonitor.MainActivity.Companion.allPacksList

class addTestPack : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_test_pack)
//        generate random pack values

        var numberOfTestMods: Int = (1..5).random()
        var cellVolt: Float = randomTwoPointDecimal(320, 410)
        var modTemp: Float = randomTwoPointDecimal(1500, 2000)


//        build the pack
        var newPack: Test.Pack.Builder = Test.Pack.newBuilder().setId(allPacksList.size +1)
                .setPackName("Test Pack ${allPacksList.size + 1}")
        modMaker(numberOfTestMods, cellVolt, modTemp, newPack)
        newPack.build()

//        pass it back


    }

//    generates Modules
    private fun modMaker(numberOfTestMods: Int, cellvolt: Float , modTemp: Float , packBuilder: Test.Pack.Builder) {
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
//    generates cells for each module
    private fun cellMaker(cellvolt: Float, modbuilder: Test.Pack.Module.Builder){
        for(i in 0 until 6) {
            modbuilder.addCells(Test.Pack.Module.Cell.newBuilder()
                    .setCellId(i + 1)
                    .setCellVolt(cellvolt)
            )
        }
    }
//     generates a random 2point decimal by taking you low and high times 100 ie "3.2 -> 320 & 4.2 -> 420"
    fun randomTwoPointDecimal(yourMinTimes100: Int, yourMaxTimes100: Int ):Float{
        val rnds = (yourMinTimes100..yourMaxTimes100).random()
        return rnds.times(0.01).toFloat()
    }

}