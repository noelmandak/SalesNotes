package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.*
import com.example.salesnotes.databinding.FragmentCustomerBinding

class CustomerFragment : Fragment() {

    private lateinit var binding: FragmentCustomerBinding
    private lateinit var viewModel: CustomerViewModel
    private lateinit var customerRecyclerView : RecyclerView
    val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentCustomerBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)


        customerRecyclerView = binding.CustomerRecycleView
        customerRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        customerRecyclerView.setHasFixedSize(true)



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle("Customer Data")

        val token = sharedViewModel.token
        viewModel.getAllCustomers(token)
        viewModel.customersLiveData.observe(viewLifecycleOwner) { customerLiveData ->
            customerRecyclerView.adapter = CustomerAdapter(viewModel.customersLiveData,viewModel)
        }

        viewModel.currentCustomer.observe(viewLifecycleOwner) {customer->
            binding.nameTextView.text = customer.name
            binding.phoneTextView.text = customer.phone
            binding.addressTextView.text = customer.address
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}