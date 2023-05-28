package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.TransactionAdapter
import com.example.salesnotes.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {
    private lateinit var binding: FragmentTransactionBinding
    private lateinit var transactionRecyclerView: RecyclerView

    val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var viewModel: TransactionViewModel


    private val mHandler = Handler()
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentTransactionBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        transactionRecyclerView = binding.TransactionRecycleView
        transactionRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        transactionRecyclerView.setHasFixedSize(true)
        transactionRecyclerView.adapter = TransactionAdapter(viewModel.transactionLiveData,viewModel)


        mRunnable = Runnable {
            viewModel.getAllTransactions(sharedViewModel.token) //otomatis update setiap 3 detik
            mHandler.postDelayed(mRunnable, 3000)
        }
        mHandler.postDelayed(mRunnable, 3000)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Transaction History"

        viewModel.getAllTransactions(sharedViewModel.token)
        viewModel.transactionLiveData.observe(viewLifecycleOwner) {
            transactionRecyclerView.adapter = TransactionAdapter(viewModel.transactionLiveData,viewModel)
        }
        viewModel.refreshTrigger.observe(viewLifecycleOwner, Observer {
            viewModel.getAllTransactions(sharedViewModel.token)
            transactionRecyclerView.adapter = TransactionAdapter(viewModel.transactionLiveData,viewModel)
        })

        return binding.root
    }


    override fun onDestroyView() {
        mHandler.removeCallbacks(mRunnable)
        super.onDestroyView()
    }
}