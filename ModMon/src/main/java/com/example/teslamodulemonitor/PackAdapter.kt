package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test.Pack
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class PackAdapter(packs: ArrayList<Pack>) : RecyclerView.Adapter<PackAdapter.PackViewHolder>() {
    var packs = packs

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class PackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var pack: Pack
        var packName: TextView = view.findViewById(R.id.packName)
        var voltHolder: ConstraintLayout = view.findViewById(R.id.voltHolder)
        var tempHolder: ConstraintLayout = view.findViewById(R.id.tempHolder)
        var moduleCount: TextView = view.findViewById(R.id.moduleCount)
        var modules : LinearLayout = view.findViewById(R.id.moduleList)

        init {
//            LayoutInflater.from(view.context).inflate(R.layout.fragment_valueholder, voltHolder)
            voltHolder.addView(Valueholder(view.context, "Volts", pack.currentVoltage))
            LayoutInflater.from(view.context).inflate(R.layout.fragment_valueholder, tempHolder)
        }
    }


    // Create new views (invoked by the layout manager)
    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PackViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pack_card_view, viewGroup, false)

        // OnClick listener
        return PackViewHolder(view).listen { position, type ->
            var modules: LinearLayout = view.findViewById(R.id.moduleList)
//            Set the visibility of the modules
            modules.visibility = if (modules.isVisible) View.GONE else View.VISIBLE
        }
//        no pack is yet assigned because position is not defined
    }



    //Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(packViewHolder: PackViewHolder, position: Int) {
        //Get pack at this position
        packViewHolder.pack = packs.get(position)


        //Replace the contents of the view with that element
        packViewHolder.packName.setText(packViewHolder.pack.packName)
        packViewHolder.moduleCount.setText("${packViewHolder.pack.modulesCount} Modules")

        packViewHolder.voltHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Volts")
        packViewHolder.voltHolder.findViewById<TextView>(R.id.value)
            .setText(packViewHolder.pack.currentVoltage.toString())

        packViewHolder.tempHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Temp")
        packViewHolder.tempHolder.findViewById<TextView>(R.id.value)
            .setText(packViewHolder.pack.averagePacktemp.toString())

        if (packViewHolder.modules.childCount < packViewHolder.pack.modulesCount) {
            for (i in 0..packViewHolder.pack.modulesCount - 1) {

                var module = LayoutInflater.from(packViewHolder.itemView.context)
                    .inflate(R.layout.module_view, null)
                module.findViewById<TextView>(R.id.modNumber).setText("Module ${i + 1}")
                packViewHolder.modules.addView(module)

                listOf<Int>(
                    R.id.cell1,
                    R.id.cell2,
                    R.id.cell3,
                    R.id.cell4,
                    R.id.cell5,
                    R.id.cell6
                ).forEachIndexed { index, cellID ->
                    var c: View = LayoutInflater.from(module.context)
                        .inflate(R.layout.fragment_valueholder, null)
                    c.id = i * 10 + 1
                    c.findViewById<TextView>(R.id.nameOfHeldValue).setText("Cell ${index + 1}")
                    c.findViewById<TextView>(R.id.value)
                        .setText(packViewHolder.pack.getModules(i).getCells(0).cellVolt.toString())
                    module.findViewById<ConstraintLayout>(cellID).addView(c)
                }
            }
        }
    }



//    Return the size of the dataset (invoked by the layout manager)
    override fun getItemCount() = packs.size

//    OnClickListener : Adapted from https://stackoverflow.com/questions/29424944/recyclerview-itemclicklistener-in-kotlin
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }
}
