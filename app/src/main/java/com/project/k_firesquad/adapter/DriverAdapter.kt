package com.project.k_firesquad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.k_firesquad.R
import com.project.k_firesquad.models.VehicleData


class DriverAdapter(private val empList:ArrayList<VehicleData>):RecyclerView.Adapter<DriverAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_with_poster,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DriverAdapter.ViewHolder, position: Int) {
        val currentEmp=empList[position]
        holder.vehicleNo.text=currentEmp.vehicleNo
        holder.vehicleType.text=currentEmp.vehicleType
        holder.vehicleOwner.text=currentEmp.owner
        holder.vehiclePrice.text=currentEmp.price
        holder.vehicleDescription.text=currentEmp.about
        holder.vehicleContact.text=currentEmp.contact
        holder.vehicleLocation.text=currentEmp.location


    }

    override fun getItemCount(): Int {
        return empList.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val vehicleNo :TextView = itemView.findViewById(R.id.vehicleNo)
        val vehicleType :TextView = itemView.findViewById(R.id.vehicleType)
        val vehicleOwner:TextView = itemView.findViewById(R.id.vehicleOwner)
        val vehiclePrice:TextView = itemView.findViewById(R.id.vehiclePrice)
        val vehicleDescription:TextView = itemView.findViewById(R.id.vehicleDescription)
        val vehicleContact:TextView = itemView.findViewById(R.id.vehicleContact)
        val vehicleLocation:TextView = itemView.findViewById(R.id.vehicleLocation)


    }
}