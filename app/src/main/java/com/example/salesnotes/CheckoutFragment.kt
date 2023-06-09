package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.ItemsCheckoutAdapter
import com.example.salesnotes.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var viewModel: CheckoutViewModel
    private lateinit var checkoutRecyclerView: RecyclerView
    private lateinit var customerViewModel: CustomerViewModel
    val sharedViewModel: SharedViewModel by activityViewModels()

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
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)

        var token = sharedViewModel.token
        customerViewModel.getAllCustomers(token)

        val spinner: Spinner = binding.selectCustomerSpinner

        customerViewModel.customersLiveData.observe(viewLifecycleOwner) { customerList->
            customerViewModel.toArray()
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, customerViewModel.arrayCustomer)
            spinner.adapter = adapter
        }
        binding.OrderButton.setOnClickListener {
            val customerSelected = spinner.selectedItem.toString()
            val customerSelectedId = customerViewModel.getIdCustomer(customerSelected)
            viewModel.createOrder(customerSelectedId)
        }
        viewModel.orderRespons.observe(viewLifecycleOwner) { orderResponse ->
            sharedViewModel.orderRespons.value = orderResponse
            findNavController().navigate(R.id.confirmationFragment)
        }

        binding.CancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_order)
        }

        checkoutRecyclerView = binding.CheckoutRecycleView
        checkoutRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        checkoutRecyclerView.setHasFixedSize(true)


        var item = sharedViewModel.checkOutItem
        viewModel.set_items(item)

        binding.TotaltextView.text = viewModel.totalPrice.value.toString()

        viewModel.items.observe(viewLifecycleOwner, Observer {
            checkoutRecyclerView.adapter = ItemsCheckoutAdapter(viewModel.items,viewModel,requireContext())
        })
        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.TotaltextView.text = viewModel.totalPrice.value.toString()
        }
    }

}