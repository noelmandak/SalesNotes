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
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

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

        val token = "bm9lbA=="
        viewModel.getAllCustomers(token)



        var customers_list =  arrayListOf(
            Customer(0,"Renata Valentarjo", "081233333333","planet mars"),
            Customer(1,"Renata Valentarjo", "081233333333","planet mars"),

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