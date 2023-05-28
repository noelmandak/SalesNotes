package com.example.salesnotes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                viewModel.searchItemsByName(searchText)
                binding.categoryDropdown.setSelection(0)
                RetrofitInstance.BASE_URL = ""
            }
        })

        binding.categoryDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString().lowercase()
                viewModel.filterItemsByCategory(selectedItem)
                Toast.makeText(requireContext(), "Andak memilih $selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        viewModel.getToastMessage().observe(this, Observer { message ->
            message?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })


    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = "Order Entry"

        viewModel.getAllItems()
        viewModel.filteredItemList.observe(viewLifecycleOwner, Observer { items ->
            productRecyclerView.adapter = ItemsAdapter(viewModel.filteredItemList,viewModel,requireContext())
        })

        return binding.root
    }
}