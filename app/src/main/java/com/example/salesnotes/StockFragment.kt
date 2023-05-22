package com.example.salesnotes

import android.R
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
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

        val spinner: Spinner = binding.categoryDropdown
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, viewModel.category)
        spinner.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle("Stock")

        viewModel.getAllStocks()
        var data = viewModel.stockLiveData.value
        data

        viewModel.stockLiveData.observe(viewLifecycleOwner) { stockLiveData ->
            stockRecyclerView.adapter = StockAdapter(viewModel.stockLiveData)
        }


        return binding.root
    }
}

