package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesnotes.data.*
import com.example.salesnotes.databinding.FragmentCustomerBinding

class CustomerFragment : Fragment() {

    private lateinit var binding: FragmentCustomerBinding
    private lateinit var viewModel: CustomerViewModel
    private lateinit var customerRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentCustomerBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)


        customerRecyclerView = binding.CustomerRecycleView
        customerRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        customerRecyclerView.setHasFixedSize(true)
        getCustomerData()

    }
    private fun getCustomerData() {
        var customers_list =  arrayListOf(
            customer("Renata Valentarjo", 0,"081233333333","planet mars"),
            customer("Renata Valentarjo", 0,"081233333333","planet mars"),

            )

        customers_list
        customerRecyclerView.adapter = CustomerAdapter(customers_list)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_customer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}