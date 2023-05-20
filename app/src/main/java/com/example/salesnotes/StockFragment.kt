package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.Stock
import com.example.salesnotes.data.StockAdapter
import com.example.salesnotes.databinding.FragmentStockBinding
import com.example.salesnotes.databinding.FragmentTransactionBinding

class StockFragment : Fragment() {

    private lateinit var binding: FragmentStockBinding
    private lateinit var stockRecyclerView: RecyclerView
//    private lateinit var stockArrayList: ArrayList<Stock>
    lateinit var itemName : Array<String>
    lateinit var itemId : Array<Int>
    lateinit var stock : Array<Int>
    lateinit var qty : Array<Int>
    lateinit var available : Array<Int>

    private lateinit var viewModel : StockViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentStockBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        stockRecyclerView = binding.StockRecycleView
        stockRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        stockRecyclerView.setHasFixedSize(true)

        stockRecyclerView.adapter = StockAdapter(viewModel.stockArrayList)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}

