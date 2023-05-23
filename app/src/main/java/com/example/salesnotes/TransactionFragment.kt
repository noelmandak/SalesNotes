package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.ItemsAdapter
import com.example.salesnotes.data.StockAdapter
import com.example.salesnotes.data.Transaction
import com.example.salesnotes.data.TransactionAdapter
import com.example.salesnotes.databinding.FragmentOrderBinding
import com.example.salesnotes.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {
    private lateinit var binding: FragmentTransactionBinding
    private lateinit var transactionRecyclerView: RecyclerView
    private lateinit var transactionArrayList: ArrayList<Transaction>
    lateinit var customerName : Array<String>
    lateinit var transactionId : Array<Int>
    lateinit var transactionValue : Array<Int>
    lateinit var transactionStatus : Array<String>

    val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentTransactionBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

//        customerName = arrayOf("Renata","Jeni","Timo")
//        transactionId = arrayOf(1,2,3)
//        transactionValue = arrayOf(5000,10000,5000)
//        transactionStatus = arrayOf("Processed","Sent","Canceled")

        transactionRecyclerView = binding.TransactionRecycleView
        transactionRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        transactionRecyclerView.setHasFixedSize(true)


        transactionRecyclerView.adapter = TransactionAdapter(viewModel.transactionLiveData,viewModel)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle("Transaction History")

        val token = sharedViewModel.token
        viewModel.getAllTransactions(token)
        var data = viewModel.transactionLiveData.value

        viewModel.transactionLiveData.observe(viewLifecycleOwner) { transactionLiveData ->
            val data =  viewModel.transactionLiveData
            data
            transactionRecyclerView.adapter = TransactionAdapter(viewModel.transactionLiveData,viewModel)
        }

        viewModel.refreshTrigger.observe(viewLifecycleOwner, Observer {
            // Lakukan refresh pada RecyclerView di sini
            viewModel.getAllTransactions(token)
            transactionRecyclerView.adapter = TransactionAdapter(viewModel.transactionLiveData,viewModel)
        })

        return binding.root
    }
}