package com.example.salesnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.ItemsAdapter
import com.example.salesnotes.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var productRecyclerView : RecyclerView
    private lateinit var productArrayList : ArrayList<Items>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentOrderBinding.inflate(layoutInflater)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val spinner: Spinner = binding.categoryDropdown
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, viewModel.category)
        spinner.adapter = adapter

        productRecyclerView = binding.ProductRecycleView
        productRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        productRecyclerView.setHasFixedSize(true)

        productRecyclerView.adapter = ItemsAdapter(viewModel.filteredProductList,viewModel)


        binding.CheckoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_order_to_checkoutFragment)
        }

//        viewModel.filteredProductList.observe(viewLifecycleOwner, Observer { productList ->
//            // Update RecyclerView with the new list of products
//            (productRecyclerView.adapter as ItemsAdapter).setData(productList)
//        })

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.setTitle("Order Entry")
        return binding.root
    }
}