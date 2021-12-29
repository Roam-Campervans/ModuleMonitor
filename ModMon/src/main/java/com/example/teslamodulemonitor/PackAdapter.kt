package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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

//            = View.OnClickListener { v ->  }
//            itemView.setOnClickListener(listener)
            LayoutInflater.from(view.context).inflate(R.layout.fragment_valueholder, view.findViewById(R.id.voltHolder))
            LayoutInflater.from(view.context).inflate(R.layout.fragment_valueholder, view.findViewById(R.id.tempHolder))


        }

//        fun bindPack(pack: Test.Pack){
////            Set Name
//            this.packName.setText(pack.packName)
////            Set Voltage
//            this.voltHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Volts")
//            this.voltHolder.findViewById<TextView>(R.id.value).setText(pack.currentVoltage.toString())
////            Set Temp
//            this.tempHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Temp")
//            this.tempHolder.findViewById<TextView>(R.id.value).setText(pack.averagePacktemp.toString())
//
//        }


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PackViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.pack_viewholder_layout, viewGroup, false)

        return PackViewHolder(view).listen{position, type ->
            var whichPack = packs.get(position)
            Toast.makeText(view.context,"You clicked ${whichPack.packName}",Toast.LENGTH_SHORT).show()
        }
    }

    //Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(packViewHolder: PackViewHolder, position: Int) {
        //Get pack at this position
        packViewHolder.pack = packs.get(position)
        //Replace the contents of the view with that element
        packViewHolder.packName.setText(packViewHolder.pack.packName)
        //TODO:make this pretty

        packViewHolder.voltHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Volts")
        packViewHolder.voltHolder.findViewById<TextView>(R.id.value).setText(packViewHolder.pack.currentVoltage.toString())
        packViewHolder.tempHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Temp")
        packViewHolder.tempHolder.findViewById<TextView>(R.id.value).setText(packViewHolder.pack.averagePacktemp.toString())
    }

//    Return the size of the dataset (invoked by the layout manager)
    override fun getItemCount() = packs.size

//    Adapted from https://stackoverflow.com/questions/29424944/recyclerview-itemclicklistener-in-kotlin
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }
}
