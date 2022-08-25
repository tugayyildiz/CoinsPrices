package com.denemeprojesi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denemeprojesi.R
import com.denemeprojesi.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val cryptoList : ArrayList<CryptoModel>,private  val listener : Listener): RecyclerView.Adapter<RecyclerViewAdapter.Rowholder>() {

    interface Listener{
        fun onItemClick(cryptoModel : CryptoModel)
    }
    private val colors : Array<String> = arrayOf("#DFFF00","#FFBF00","#FF7F50","#DE3163","#9FE2BF","#40E0D0","#6495ED","#CCCCFF")

    class Rowholder(view:View) : RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel : CryptoModel,colors:Array<String>,position: Int , listener:Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.text_name.text = cryptoModel.currency
            itemView.text_price.text = cryptoModel.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rowholder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return Rowholder(view)
    }

    override fun getItemCount(): Int {

        return cryptoList.count()
    }

    override fun onBindViewHolder(holder: Rowholder, position: Int) {

        holder.bind(cryptoList[position],colors,position,listener)

    }
}