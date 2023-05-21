package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.Items
import com.example.salesnotes.data.ItemsAdapter
import com.example.salesnotes.data.ItemsCheckout
import com.example.salesnotes.data.ItemsCheckoutAdapter
import com.example.salesnotes.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var viewModel: CheckoutViewModel
    private lateinit var checkoutRecyclerView: RecyclerView

    val sharedViewModel: SharedViewModel by activityViewModels()



    private fun getCheckoutData() {
        var products =  arrayListOf(
            ItemsCheckout("Nasi Goreng",15000,10,2, R.drawable.nasigoreng),
            ItemsCheckout("Ayam Goreng",17000,10,1, R.drawable.ayamgoreng),
            ItemsCheckout("Pisang Goreng",5000,10,1, R.drawable.pisanggoreng),
            ItemsCheckout("Tahu Goreng",5000,10,1, R.drawable.tahugoreng),
            ItemsCheckout("Mie Goreng",15000,10,1, R.drawable.miegoreng),
        )
//        productArrayList = products
//        checkoutRecyclerView.adapter = ItemsCheckoutAdapter(products)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle("Checkout")

        binding = FragmentCheckoutBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CheckoutViewModel::class.java)




        binding.OrderButton.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_customerFragment)
        }
        binding.CancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_order)
        }

        checkoutRecyclerView = binding.CheckoutRecycleView
        checkoutRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        checkoutRecyclerView.setHasFixedSize(true)

        getCheckoutData()

        var item = sharedViewModel.checkOutItem
        viewModel.set_items(item)


        binding.TotaltextView.text = viewModel.totalPrice.value.toString()

        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            checkoutRecyclerView.adapter = ItemsCheckoutAdapter(viewModel.items,viewModel,requireContext())
        })
        viewModel.totalPrice.observe(viewLifecycleOwner, {totalPrice ->
            binding.TotaltextView.text = viewModel.totalPrice.value.toString()
        })
    }

}