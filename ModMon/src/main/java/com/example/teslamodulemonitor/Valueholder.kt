package com.example.teslamodulemonitor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

class Valueholder(context: Context, valueName: String, value: Float) : View(context) {

    init {
        var view = LayoutInflater.from(context).inflate(R.layout.fragment_valueholder,null)
        view.findViewById<TextView>(R.id.nameOfHeldValue).setText(valueName)
        view.findViewById<TextView>(R.id.value).setText(value.toString())
    }

}
