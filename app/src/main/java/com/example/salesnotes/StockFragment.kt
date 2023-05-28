package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.StockAdapter
import com.example.salesnotes.databinding.FragmentStockBinding

class StockFragment : Fragment() {

    private lateinit var binding: FragmentStockBinding
    private lateinit var stockRecyclerView: RecyclerView

    private lateinit var viewModel : StockViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentStockBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        stockRecyclerView = binding.StockRecycleView
        stockRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        stockRecyclerView.setHasFixedSize(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Stock"

        viewModel.getAllStocks()

        viewModel.stockLiveData.observe(viewLifecycleOwner) {
            stockRecyclerView.adapter = StockAdapter(viewModel.stockLiveData)
        }

        viewModel.getToastMessage().observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}

