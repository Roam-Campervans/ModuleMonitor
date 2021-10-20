package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PackAdapter(private val packs: ArrayList<Test.Pack>) :
        RecyclerView.Adapter<PackAdapter.PackViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class PackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val packVoltFrag: ValueHolderFrag
        val packTempFrag: ValueHolderFrag


//        Constructor!!!
        init {
            // Define click listener for the ViewHolder's View.
//            Define fillable fields and fragments
            textView =view.findViewById(R.id.packName)
            packVoltFrag = ValueHolderFrag.newInstance("Volts",0f)
            packTempFrag = ValueHolderFrag.newInstance("Temp",0f)

        }
    }

//    abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view){
//        abstract fun onBind(test: Test.Pack) {
//
//        }
//    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PackViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.fragment_pack, viewGroup, false)

        return PackViewHolder(view)
    }



    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(packViewHolder: PackViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        packViewHolder.textView.text = packs[position].packName

//        val voltView = LayoutInflater.from(viewGroup.context).inflate(R.layout.fragment_valueholder,viewGroup,false)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = packs.size

}
