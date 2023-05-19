package com.example.salesnotes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.R

class CustomerAdapter(private val customerList: ArrayList<customer>):RecyclerView.Adapter<CustomerAdapter.CustomerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder {
        val customerView = LayoutInflater.from(parent.context).inflate(R.layout.list_customer, parent, false)


        return CustomerHolder(customerView)
    }


    override fun onBindViewHolder(holder: CustomerHolder, position: Int) {
        val currentCustomer = customerList[position]

        currentCustomer
        holder.nameCustomer.text = currentCustomer.name
        holder.idCustomer.text = currentCustomer.id.toString()

    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    class CustomerHolder(customerView: View) : RecyclerView.ViewHolder(customerView){
        val nameCustomer : TextView = customerView.findViewById(R.id.theCustomerName)
        val idCustomer : TextView = customerView.findViewById(R.id.theCustomerId)


    }


}