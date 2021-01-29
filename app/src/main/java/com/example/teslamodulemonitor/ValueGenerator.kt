package com.example.teslamodulemonitor
import TeslaModuleMonitor.Test
import java.lang.Integer.MAX_VALUE
import kotlin.random.Random.Default.nextFloat
import kotlin.random.Random.Default.nextInt

data class ValueGenerator(
        var cellVolt: Float = randomTwoPointDecimal(320,410),
        var modTemp: Float = randomTwoPointDecimal(1500,2000)
)

fun randomTwoPointDecimal(minDivByPoint01: Int, maxDivByPoint01: Int ):Float{
    val rnds = (minDivByPoint01..maxDivByPoint01).random()
    return rnds.times(0.01).toFloat()
}