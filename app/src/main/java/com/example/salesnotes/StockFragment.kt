package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.StockAdapter
import com.example.salesnotes.databinding.FragmentStockBinding

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
        val token = "bm9lbA=="
        binding = FragmentStockBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        stockRecyclerView = binding.StockRecycleView
        stockRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        stockRecyclerView.setHasFixedSize(true)

        viewModel.getAllStocks(token)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        stockRecyclerView.adapter = StockAdapter(viewModel.stockLiveData)
        return binding.root
    }
}

