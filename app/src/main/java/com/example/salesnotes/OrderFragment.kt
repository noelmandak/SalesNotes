package com.example.salesnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.ItemsAdapter
import com.example.salesnotes.data.TokenManager
import com.example.salesnotes.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var productRecyclerView : RecyclerView
    private lateinit var productArrayList : ArrayList<Items>

    val sharedViewModel: SharedViewModel by activityViewModels()
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

        binding.CheckoutButton.setOnClickListener {
            var items = viewModel.goToCheckout()
            if (items.size>0){
                sharedViewModel.checkOutItem = items
                findNavController().navigate(R.id.action_order_to_checkoutFragment)
            } else {
                Toast.makeText(context, "Pilih setidaknya 1 barang", Toast.LENGTH_LONG).show()
            }

        }

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.setTitle("Order Entry")

        viewModel.getAllItems()
        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            productRecyclerView.adapter = ItemsAdapter(viewModel.items,viewModel,requireContext())
        })
//        productRecyclerView.adapter = ItemsAdapter(viewModel.items,viewModel,requireContext())


        return binding.root
    }
}