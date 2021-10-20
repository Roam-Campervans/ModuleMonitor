package com.example.teslamodulemonitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NAME = "name"
private const val VOLTS = "volts"
private const val TEMP = "temp"

/**
 * A simple [Fragment] subclass.
 * Use the [PackFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class PackFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private var volts: Float? = null
    private var temp: Float? = null
    private var voltholder: ValueHolderFrag? = null
    private var tempholder: ValueHolderFrag? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            volts = it.getFloat(VOLTS)
            temp = it.getFloat(TEMP)
            voltholder = ValueHolderFrag.newInstance("Volts", volts!!)
            tempholder = ValueHolderFrag.newInstance("Temp", temp!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pack, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param name PackName.
         * @param volts PackVoltage.
         * @param temp PackTemp.
         * @return A new instance of fragment PackFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, volts: Float, temp: Float) =
                PackFrag().apply {
                    arguments = Bundle().apply {
                        putString(NAME, name)
                        putFloat(VOLTS, volts)
                        putFloat(TEMP, temp)
                    }
                }
    }
}