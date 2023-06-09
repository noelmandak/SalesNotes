package com.example.salesnotes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.CustomerViewModel
import com.example.salesnotes.R
import com.example.salesnotes.TransactionViewModel
import java.text.NumberFormat
import java.util.*

class TransactionAdapter(private val transactionList : MutableLiveData<List<Transaction>>, private val viewModel: TransactionViewModel) :
    RecyclerView.Adapter<TransactionAdapter.transactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): transactionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_transaction, parent, false)
        return transactionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transactionList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: transactionViewHolder, position: Int) {
        val currentTransaction = transactionList.value?.get(position)
        val formatter = NumberFormat.getInstance(Locale.getDefault())

        if (currentTransaction != null) {
            holder.customerName.text = currentTransaction.customerName
            holder.transactionId.text = "ID: ${currentTransaction.transactionId}"
            holder.transactionValue.text =  formatter.format(currentTransaction.transactionValue)
            holder.transactionStatus.text = currentTransaction.transactionStatus
            holder.buttonCancel.isEnabled = currentTransaction.transactionStatus == "Active"
            holder.tanggal.text = currentTransaction.transactionDate.toString()
            holder.buttonCancel.setOnClickListener {
                viewModel.cancelOrder(currentTransaction.transactionId.toString())
            }
        }
    }

    class transactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val customerName : TextView  = itemView.findViewById(R.id.namaCustomer)
        val transactionId : TextView = itemView.findViewById(R.id.idTransaksi)
        val transactionValue : TextView = itemView.findViewById(R.id.nilaiTransaksi)
        val transactionStatus : TextView = itemView.findViewById(R.id.status)
        val buttonCancel : Button = itemView.findViewById(R.id.buttonCancel)
        val tanggal : TextView = itemView.findViewById(R.id.tanggal)
    }


}
