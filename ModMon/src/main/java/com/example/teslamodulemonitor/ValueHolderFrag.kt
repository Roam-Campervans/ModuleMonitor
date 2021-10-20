package com.example.teslamodulemonitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NAME = "name"
private const val VALUE = "value"


/**
 * A simple [Fragment] subclass.
 * Use the [ValueHolderFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class ValueHolderFrag : Fragment() {
    // TODO: Rename and change types of parameters

    private var name: String? = null
    private var value: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            value = it.getFloat(VALUE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_valueholder, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param name Parameter 1.
         * @param value Parameter 2.
         * @return A new instance of fragment VoltFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String?, value: Float) =
                ValueHolderFrag().apply {
                    arguments = Bundle().apply {
                        putString(NAME, name)
                        putFloat(VALUE, value)
                    }
                }
    }
}