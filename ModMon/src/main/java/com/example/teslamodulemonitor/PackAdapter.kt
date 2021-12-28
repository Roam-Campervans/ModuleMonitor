package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class PackAdapter(packs: ArrayList<Test.Pack>) : RecyclerView.Adapter<PackAdapter.PackViewHolder>() {
    var packs = packs

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class PackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var pack: Test.Pack
        var packName: TextView = view.findViewById(R.id.packName)
        var voltHolder: ConstraintLayout = view.findViewById(R.id.voltHolder)
        var tempHolder: ConstraintLayout = view.findViewById(R.id.tempHolder)

        init {
//           TODO: Define click listener for the ViewHolder's View.
//            Define fillable fields and fragments
            LayoutInflater.from(view.context).inflate(R.layout.fragment_valueholder, view.findViewById(R.id.voltHolder))
            LayoutInflater.from(view.context).inflate(R.layout.fragment_valueholder, view.findViewById(R.id.tempHolder))


        }

        fun bindPack(pack: Test.Pack){
            this.pack = pack
//            Set Name
            this.packName.setText(pack.packName)
//            Set Voltage
            this.voltHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Volts")
            this.voltHolder.findViewById<TextView>(R.id.value).setText(pack.currentVoltage.toString())
//            Set Temp
            this.tempHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Temp")
            this.tempHolder.findViewById<TextView>(R.id.value).setText(pack.averagePacktemp.toString())

        }


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PackViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.pack_viewholder_layout, viewGroup, false)

        return PackViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(packViewHolder: PackViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        packViewHolder.bindPack(packs[position])
       //Need to add it but no FragmentManager :(

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = packs.size
}
