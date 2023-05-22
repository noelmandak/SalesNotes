package com.example.salesnotes.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.R

class TransactionAdapter(private val transactionList : MutableLiveData<List<Transaction>>) :
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
        if (currentTransaction != null) {
        holder.customerName.text = currentTransaction.customerName
        holder.transactionId.text = "ID: ${currentTransaction.transactionId}"
        holder.transactionValue.text = currentTransaction.transactionValue.toString()
        holder.transactionStatus.text = currentTransaction.transactionStatus
        holder.buttonCancel.isEnabled = currentTransaction.transactionStatus == "Processed"
        holder.tanggal.text = currentTransaction.transactionDate.toString()
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