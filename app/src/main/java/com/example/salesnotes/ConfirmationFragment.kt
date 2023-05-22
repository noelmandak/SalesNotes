package com.example.salesnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.salesnotes.databinding.ConfirmationBinding
import com.example.salesnotes.databinding.FragmentConfirmationBinding
import java.text.NumberFormat
import java.util.*

class ConfirmationFragment : Fragment() {


    private lateinit var viewModel: ConfirmationViewModel
    private lateinit var binding: FragmentConfirmationBinding
    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ConfirmationViewModel::class.java)
        binding = FragmentConfirmationBinding.inflate(layoutInflater)

        val response = sharedViewModel.orderRespons.value
        val formatter = NumberFormat.getInstance(Locale.getDefault())


        if (response != null) {
            binding.transactionId.text = response.transactionId
            binding.totalTextView.text = "Rp ${formatter.format(response.total)}"
        }
        binding.orderLagiButton.setOnClickListener {
            findNavController().navigate(R.id.order)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    // TODO: Use the ViewModel
    }

}