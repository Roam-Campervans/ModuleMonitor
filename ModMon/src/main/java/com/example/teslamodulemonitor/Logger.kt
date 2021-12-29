package com.example.teslamodulemonitor

import android.util.Log

class Logger {
    companion object {
        private val TAG = "ModMon"

        fun i(activity: String?, message: String) {
            Log.i("$TAG.$activity", message)
        }

        fun w(activity: String?, warning: String) {
            Log.w("$TAG.$activity", warning)
        }
    }
}
