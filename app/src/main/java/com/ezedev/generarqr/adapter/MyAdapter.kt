package com.ezedev.generarqr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ezedev.generarqr.R
import com.ezedev.generarqr.model.ModelList

class MyAdapter(private val newList : ArrayList<ModelList>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_user,parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.listado.text = currentItem.listado
    }

    override fun getItemCount(): Int {
        return newList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val listado : TextView = itemView.findViewById(R.id.TxtNombre)
    }

}