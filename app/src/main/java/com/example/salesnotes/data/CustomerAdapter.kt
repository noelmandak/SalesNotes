package com.example.salesnotes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.CheckoutViewModel
import com.example.salesnotes.CustomerViewModel
import com.example.salesnotes.R

class CustomerAdapter(private val customerList: MutableLiveData<List<Customer>>, private val viewModel: CustomerViewModel):RecyclerView.Adapter<CustomerAdapter.CustomerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder {
        val customerView = LayoutInflater.from(parent.context).inflate(R.layout.list_customer, parent, false)


        return CustomerHolder(customerView)
    }


    override fun onBindViewHolder(holder: CustomerHolder, position: Int) {
        val currentCustomer = customerList.value?.get(position)

        currentCustomer
        if (currentCustomer != null) {
            holder.nameCustomer.text = currentCustomer.name
            holder.idCustomer.text = currentCustomer.id_customer.toString()
            holder.customerItem.setOnClickListener{
                viewModel.changeCustomerDetail(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return customerList.value?.size ?: 0
    }

    class CustomerHolder(customerView: View) : RecyclerView.ViewHolder(customerView){
        val nameCustomer : TextView = customerView.findViewById(R.id.theCustomerName)
        val idCustomer : TextView = customerView.findViewById(R.id.theCustomerId)
        val customerItem : LinearLayout = customerView.findViewById(R.id.customerItem)
    }


}