package com.example.teslamodulemonitor

import TeslaModuleMonitor.Test
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
        var moduleCount: TextView = view.findViewById(R.id.moduleCount)

        init {
            LayoutInflater.from(view.context).inflate(R.layout.fragment_valueholder, view.findViewById(R.id.voltHolder))
            LayoutInflater.from(view.context).inflate(R.layout.fragment_valueholder, view.findViewById(R.id.tempHolder))
        }

    }

    // Create new views (invoked by the layout manager)
    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PackViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pack_card_view, viewGroup, false)

        return PackViewHolder(view).listen { position, type ->
            var whichPack = packs.get(position)

            for (i in 0..whichPack.modulesCount - 1) {
                Logger.i("PackView holder", "module $i")


                var module = LayoutInflater.from(view.context).inflate(R.layout.module_view, null)
                view.findViewById<LinearLayout>(R.id.moduleList).addView(module)


                listOf<Int>(
                    R.id.cell1,
                    R.id.cell2,
                    R.id.cell3,
                    R.id.cell4,
                    R.id.cell5,
                    R.id.cell6
                ).forEachIndexed { index, cellID ->
                    var c: View = LayoutInflater.from(view.context)
                        .inflate(R.layout.fragment_valueholder, null)
                    c.id = i * 10 + 1
                    c.findViewById<TextView>(R.id.nameOfHeldValue).setText("Cell ${index + 1}")
                    c.findViewById<TextView>(R.id.value)
                        .setText(whichPack.getModules(i).getCells(0).cellVolt.toString())
                    module.findViewById<ConstraintLayout>(cellID).addView(c)
                }
            }
        }
    }

    //Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(packViewHolder: PackViewHolder, position: Int) {
        //Get pack at this position
        var pack = packs.get(position)
        packViewHolder.pack = pack
        //Replace the contents of the view with that element
        packViewHolder.packName.setText(packViewHolder.pack.packName)
        packViewHolder.moduleCount.setText("${pack.modulesCount} Modules")
        packViewHolder.voltHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Volts")
        packViewHolder.voltHolder.findViewById<TextView>(R.id.value).setText(pack.currentVoltage.toString())
        packViewHolder.tempHolder.findViewById<TextView>(R.id.nameOfHeldValue).setText("Temp")
        packViewHolder.tempHolder.findViewById<TextView>(R.id.value).setText(pack.averagePacktemp.toString())
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
